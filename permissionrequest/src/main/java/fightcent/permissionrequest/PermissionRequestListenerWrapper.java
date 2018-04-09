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
    public void onReceivedOnHasPermissionsEvent(OnHasPermissionsEvent onHasPermissionsEvent) {
        if (mPermissionRequestListener != null) {
            mPermissionRequestListener.onHasPermissions();
        }
    }

    @Subscribe
    public void onReceivedOnHasNotPermissionsEvent(OnHasNotPermissionsEvent onHasNotPermissionsEvent) {
        if (mPermissionRequestListener != null) {
            mPermissionRequestListener.onHasNotPermissions();
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
