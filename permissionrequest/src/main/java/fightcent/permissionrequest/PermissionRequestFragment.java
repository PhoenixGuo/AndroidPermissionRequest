package fightcent.permissionrequest;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class PermissionRequestFragment extends Fragment {

    public static final String PERMISSIONS = "PERMISSIONS";
    public static final String REQUEST_CODE = "REQUEST_CODE";
    private String[] mPermissions;
    private int mRequestCode;
    private AlertDialog mAlertDialog;

    public static fightcent.permissionrequest.PermissionRequestFragment makeFragment(
            String[] permissions,
            int requestCode
    ) {
        fightcent.permissionrequest.PermissionRequestFragment permissionRequestFragment
                = new fightcent.permissionrequest.PermissionRequestFragment();
        Bundle arguments = new Bundle();
        arguments.putStringArray(PERMISSIONS, permissions);
        arguments.putInt(REQUEST_CODE, requestCode);
        permissionRequestFragment.setArguments(arguments);
        return permissionRequestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mPermissions = arguments.getStringArray(PERMISSIONS);
            mRequestCode = arguments.getInt(REQUEST_CODE);
            requestPermissions(
                    mPermissions,
                    mRequestCode
            );
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
                boolean isHasAllPermissions = false;
                for (int i = 0; i < length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        boolean b = shouldShowRequestPermissionRationale(permissions[i]);
                        if (!b) {
                            //用户之前拒绝了权限并勾选了不再提醒
                            showPermissionRequestDialog();
                            return;
                        } else {
                            //没权限
                            isHasAllPermissions = false;
                            break;
                        }
                    } else {
                        isHasAllPermissions = true;
                    }
                }
                if (isHasAllPermissions) {
                    //有权限
                    EventBus.getDefault().post(new OnHasPermissionsEvent());
                } else {
                    //没权限
                    EventBus.getDefault().post(new OnHasNotPermissionsEvent());
                }
            }
        }
    }

    private void showPermissionRequestDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = PermissionRequestDialog.makeDialog(
                    getActivity(),
                    null,
                    R.string.app_name,
                    R.string.app_name,
                    R.string.app_name,
                    R.string.app_name
            );
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new OnPermissionRequestFragmentDestroyEvent());
    }

}
