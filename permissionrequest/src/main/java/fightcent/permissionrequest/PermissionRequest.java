package fightcent.permissionrequest;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class PermissionRequest {

    private static final int REQUEST_CODE = 1990;

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
            permissionRequestListener.onAllowAllPermissions();
        } else {
            PermissionRequestListenerWrapper permissionRequestListenerWrapper
                    = new PermissionRequestListenerWrapper(permissionRequestListener);
            if (!EventBus.getDefault().isRegistered(permissionRequestListenerWrapper)) {
                EventBus.getDefault().register(permissionRequestListenerWrapper);
            }

            PermissionRequestFragment permissionRequestFragment
                    = PermissionRequestFragment.makeFragment(REQUEST_CODE);

            if (mActivity != null) {
                mActivity.getFragmentManager().beginTransaction().add(
                        permissionRequestFragment,
                        "PermissionRequestFragment"
                ).commitAllowingStateLoss();
                mActivity.getFragmentManager().executePendingTransactions();

                if (permissionRequestFragment.isAdded()) {
                    permissionRequestFragment.requestPermissions(
                            permissions,
                            REQUEST_CODE
                    );
                }
            }
        }
    }

}
