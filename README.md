# AndroidPermissionRequest
一个优雅的Android6.0动态权限申请库
## 1、如何使用？
### （1）声明你需要申请的权限
``` java
final String[] permissions = new String[]{
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.CAMERA,
    Manifest.permission.CALL_PHONE
};
```
### （2）创建PermissionRequest类并调用request方法
``` java
new PermissionRequest(
                getActivity()
        ).request(
                permissions,
                new PermissionRequestListener() {
                    @Override
                    public void onAllowAllPermissions() {
                        //所有申请的权限均被允许
                    }

                    @Override
                    public void onDenySomePermissions(Collection<String> denyPermissions) {
                        //申请的权限中有权限被拒绝
                    }

                    @Override
                    public void onDenyAndNeverAskAgainSomePermissions(
                            Collection<String> denyAndNeverAskAgainPermissions
                    ) {
                        //申请的权限中有权限被拒绝并勾选了不再提示
                    }
                }
        );
```
### （3）在PermissionRequestListener的回调方法中实现自己的业务即可
## 2、效果
### （1）有权限被拒绝
![image](https://github.com/PhoenixGuo/AndroidPermissionRequest/blob/master/gif/Deny.gif)
### （2）有权限被拒绝并勾选了不再提示
![image](https://github.com/PhoenixGuo/AndroidPermissionRequest/blob/master/gif/DenyAndNeverAsk.gif)
### （3）所有权限均被允许
![image](https://github.com/PhoenixGuo/AndroidPermissionRequest/blob/master/gif/AllAllow.gif)
## 3、感谢RxPermissions库
[RxPermissions](https://github.com/tbruyelle/RxPermissions)
