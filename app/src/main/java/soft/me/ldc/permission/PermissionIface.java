package soft.me.ldc.permission;

import java.util.List;

public interface PermissionIface {
    //成功
    void onGranted();
    //失败
    void onDenied(List<String> deniedPermissions);
}
