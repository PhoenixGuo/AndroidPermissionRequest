package fightcent.permissionrequest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class PermissionRequest {

    private static final int REQUEST_CODE = 1990;
    private static final String FRAGMENT_TAG = "PermissionRequestFragment";

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

            if (mActivity != null) {
                PermissionRequestFragment permissionRequestFragment;
                Fragment fragment = mActivity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                if (fragment != null && fragment instanceof PermissionRequestFragment) {
                    permissionRequestFragment = (PermissionRequestFragment) fragment;
                } else {
                    permissionRequestFragment
                            = PermissionRequestFragment.makeFragment(REQUEST_CODE);
                    mActivity.getFragmentManager().beginTransaction().add(
                            permissionRequestFragment,
                            FRAGMENT_TAG
                    ).commitAllowingStateLoss();
                    mActivity.getFragmentManager().executePendingTransactions();
                }
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
