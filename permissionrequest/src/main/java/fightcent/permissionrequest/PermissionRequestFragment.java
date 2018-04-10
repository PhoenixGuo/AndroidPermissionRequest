package fightcent.permissionrequest;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashSet;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class PermissionRequestFragment extends Fragment {

    public static final String REQUEST_CODE = "REQUEST_CODE";
    private int mRequestCode;

    public static PermissionRequestFragment makeFragment(
            int requestCode
    ) {
        PermissionRequestFragment permissionRequestFragment
                = new PermissionRequestFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(REQUEST_CODE, requestCode);
        permissionRequestFragment.setArguments(arguments);
        return permissionRequestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mRequestCode = arguments.getInt(REQUEST_CODE);
        }
        return view;
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == mRequestCode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int length = grantResults.length;
                LinkedHashSet<String> denyAndNeverAskAgainPermissions = new LinkedHashSet<>();
                LinkedHashSet<String> denyPermissions = new LinkedHashSet<>();
                for (int i = 0; i < length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        boolean b = shouldShowRequestPermissionRationale(permissions[i]);
                        if (!b) {
                            //申请的权限中有权限被拒绝并勾选了不再提示
                            denyAndNeverAskAgainPermissions.add(permissions[i]);
                        } else {
                            //申请的权限仅仅是被用户拒绝
                            denyPermissions.add(permissions[i]);
                        }
                    }
                }
                if (denyAndNeverAskAgainPermissions.size() > 0) {
                    //申请的权限中有权限被拒绝并勾选了不再提示
                    EventBus.getDefault().post(
                            new OnDenyAndNeverAskAgainSomePermissionsEvent(
                                    denyAndNeverAskAgainPermissions
                            )
                    );
                } else if (denyPermissions.size() > 0) {
                    //申请的权限中有权限被拒绝
                    EventBus.getDefault().post(new OnDenySomePermissionsEvent(
                            denyPermissions
                    ));
                } else {
                    //所有申请的权限均被允许
                    EventBus.getDefault().post(new OnAllowAllPermissionsEvent());
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new OnPermissionRequestFragmentDestroyEvent());
    }

}
