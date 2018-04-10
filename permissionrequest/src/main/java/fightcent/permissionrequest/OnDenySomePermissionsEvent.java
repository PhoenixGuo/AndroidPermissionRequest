package fightcent.permissionrequest;

import java.util.Collection;

/**
 * Created by andy.guo on 2018/4/9.
 */

public class OnDenySomePermissionsEvent {

    private Collection<String> mDenyPermissions;

    public OnDenySomePermissionsEvent(Collection<String> denyPermissions) {
        mDenyPermissions = denyPermissions;
    }

    public Collection<String> getDenyPermissions() {
        return mDenyPermissions;
    }

}
