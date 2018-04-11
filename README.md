# AndroidPermissionRequest
一个优雅的Android6.0动态权限申请库。只要你可以获取到Activity，你可以在任何地方调用他来请求动态权限。  
另外，完全不需要你再次在Activity或Fragment的onRequestPermissionsResult方法中做任何操作。  
如果你需要的话，监听器函数中会在参数里告诉你，哪些权限被用户拒绝了、哪些权限被用户勾选了不再提示。你可以取出这些权限，对他们做单独的处理，也可以不关心他们，直接在监听器的回调函数中做统一处理。  

**如果该库对你有帮助，欢迎点一下Star，谢谢！**

## 1、相关文章链接
[Android6.0动态权限申请总结](https://blog.csdn.net/arimakisho/article/details/79872502)  
[2步搞定动态权限：一个优雅的Android6.0动态权限申请库](https://blog.csdn.net/arimakisho/article/details/79862348)
## 2、如何使用？
### （1）声明你需要申请的权限
``` java
final String[] permissions = new String[]{
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.CAMERA,
    Manifest.permission.CALL_PHONE
};
```
### （2）创建PermissionRequest类对象并调用request方法
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
## 3、效果
### （1）有权限被拒绝
![image](https://github.com/PhoenixGuo/AndroidPermissionRequest/blob/master/gif/Deny.gif)
### （2）有权限被拒绝并勾选了不再提示
![image](https://github.com/PhoenixGuo/AndroidPermissionRequest/blob/master/gif/DenyAndNeverAsk.gif)
### （3）所有权限均被允许
![image](https://github.com/PhoenixGuo/AndroidPermissionRequest/blob/master/gif/AllAllow.gif)
## 4、感谢RxPermissions库
从该库中得到了我所需要的解决方案。
[RxPermissions](https://github.com/tbruyelle/RxPermissions)
