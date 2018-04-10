package fightcent.permissionrequest;

import java.util.Collection;

/**
 * Created by andy.guo on 2016/9/12.
 */
public interface PermissionRequestListener {

    /**
     * 所有申请的权限均被允许的回调
     */
    void onAllowAllPermissions();

    /**
     * 申请的权限中有权限被拒绝的回调
     */
    void onDenySomePermissions(Collection<String> denyPermissions);

    /**
     * 申请的权限中有权限被拒绝并勾选了不再提示的回调
     */
    void onDenyAndNeverAskAgainSomePermissions(Collection<String> denyAndNeverAskAgainPermissions);

}
