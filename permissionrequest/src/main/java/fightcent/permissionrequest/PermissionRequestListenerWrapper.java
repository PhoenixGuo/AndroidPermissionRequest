package fightcent.permissionrequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class PermissionRequestListenerWrapper {

    private PermissionRequestListener mPermissionRequestListener;

    public PermissionRequestListenerWrapper(PermissionRequestListener permissionRequestListener) {
        mPermissionRequestListener = permissionRequestListener;
    }

    @Subscribe
    public void onReceivedOnAllowAllPermissionsEvent(
            OnAllowAllPermissionsEvent onAllowAllPermissionsEvent
    ) {
        if (mPermissionRequestListener != null) {
            mPermissionRequestListener.onAllowAllPermissions();
        }
    }

    @Subscribe
    public void onReceivedOnDenySomePermissionsEvent(
            OnDenySomePermissionsEvent onDenySomePermissionsEvent
    ) {
        if (mPermissionRequestListener != null) {
            mPermissionRequestListener.onDenySomePermissions(
                    onDenySomePermissionsEvent.getDenyPermissions()
            );
        }
    }

    @Subscribe
    public void onReceivedOnDenyAndNeverAskAgainSomePermissionsEvent(
            OnDenyAndNeverAskAgainSomePermissionsEvent onDenyAndNeverAskAgainSomePermissionsEvent
    ) {
        if (mPermissionRequestListener != null) {
            mPermissionRequestListener.onDenyAndNeverAskAgainSomePermissions(
                    onDenyAndNeverAskAgainSomePermissionsEvent.getDenyAndNeverAskAgainPermissions()
            );
        }
    }

    @Subscribe
    public void onReceivedOnPermissionRequestFragmentDestroyEvent(
            OnPermissionRequestFragmentDestroyEvent onPermissionRequestFragmentDestroyEvent
    ) {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
