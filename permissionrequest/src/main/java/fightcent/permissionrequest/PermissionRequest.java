package fightcent.permissionrequest;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class PermissionRequest {

    private static final int REQUEST_CODE = 1000;

    //传入Activity
    private Activity mActivity;

    public PermissionRequest(
            @NonNull Activity activity
    ) {
        mActivity = activity;
    }

    public void request(
            @NonNull String[] permissions,
            @NonNull PermissionRequestListener permissionRequestListener
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //系统低于6.0，无需动态申请权限。直接调用有权限的业务方法
            permissionRequestListener.onAllowAllPermissions();
        } else {
            PermissionRequestListenerWrapper permissionRequestListenerWrapper
                    = new PermissionRequestListenerWrapper(permissionRequestListener);
            if (!EventBus.getDefault().isRegistered(permissionRequestListenerWrapper)) {
                EventBus.getDefault().register(permissionRequestListenerWrapper);
            }

            PermissionRequestFragment permissionRequestFragment
                    = PermissionRequestFragment.makeFragment(
                    permissions,
                    REQUEST_CODE
            );

            if (mActivity != null) {
                mActivity.getFragmentManager().beginTransaction().add(
                        permissionRequestFragment,
                        "PermissionRequestDialogFragment"
                ).commit();
            }
        }

    }

}
