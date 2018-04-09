package fightcent.permissionrequest;

/**
 * Created by andy.guo on 2016/9/12.
 */
public interface PermissionRequestListener {

    /**
     * 如果检查权限，发现有该权限时的回调
     */
    void onHasPermissions();

    /**
     * 如果检查权限，发现无该权限时的回调
     */
    void onHasNotPermissions();

}
