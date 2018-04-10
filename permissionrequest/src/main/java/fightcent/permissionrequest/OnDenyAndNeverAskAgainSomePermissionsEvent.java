package fightcent.permissionrequest;

import java.util.Collection;

/**
 * Created by andy.guo on 2018/4/10.
 */

public class OnDenyAndNeverAskAgainSomePermissionsEvent {

    private Collection<String> mDenyAndNeverAskAgainPermissions;

    public OnDenyAndNeverAskAgainSomePermissionsEvent(
            Collection<String> denyAndNeverAskAgainPermissions
    ) {
        mDenyAndNeverAskAgainPermissions = denyAndNeverAskAgainPermissions;
    }

    public Collection<String> getDenyAndNeverAskAgainPermissions() {
        return mDenyAndNeverAskAgainPermissions;
    }
}
