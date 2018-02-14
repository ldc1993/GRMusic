package soft.me.ldc.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.permission.PermissionIface;
import soft.me.ldc.service.PlayService;
import soft.me.ldc.view.GRToastView;


/**
 * Created by liudi on 2018/1/10.
 */

public abstract class RootActivity extends AppCompatActivity {
    protected PlayService.ServiceBind playService = null;
    protected Context ctx;
    protected Activity act;
    protected FragmentManager fragmentManager;
    private Unbinder unbinder = null;
    //权限申请
    PermissionIface miface = null;
    final static int requestPermissionCode = 0x999;

    protected ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            playService = (PlayService.ServiceBind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.ctx = this;
            this.act = this;
            this.fragmentManager = this.getSupportFragmentManager();
            //音乐服务
            bindService(new Intent(this, PlayService.class), serviceConnection, BIND_AUTO_CREATE);
            NewCreate(savedInstanceState);
            setContentView(UI());
            unbinder = ButterKnife.bind(this);
            Main();
        } catch (Exception e) {
            GRToastView.show(ctx, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT);
            e.printStackTrace();
        }

    }

    protected abstract void NewCreate(@Nullable Bundle savedInstanceState);

    protected abstract Integer UI();

    // TODO: 2018/1/10 程序入口
    protected abstract void Main();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }

    // TODO: 2017/10/8 请求运行时权限
    public void requestRunTimePermission(String[] permissions, PermissionIface iface) {
        Activity activity = ActivityList.getTopActivity();
        miface = iface;
        if (activity == null)
            return;
        List<String> NoGranted = new ArrayList<>();
        for (String permission : permissions) {
            //未授权
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                NoGranted.add(permission);
            }
        }
        if (!NoGranted.isEmpty()) {
            ActivityCompat.requestPermissions(activity, NoGranted.toArray(new String[NoGranted.size()]), requestPermissionCode);
        } else {
            //授权成功
            miface.onGranted();
        }


    }

    // TODO: 2017/10/8 权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case requestPermissionCode:
                if (permissions.length > 0) {
                    //拒绝权限
                    List<String> deniedpermission = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int granted = grantResults[i];
                        String permission = permissions[i];
                        if (granted != PackageManager.PERMISSION_GRANTED) {
                            deniedpermission.add(permission);
                        }
                    }
                    if (deniedpermission.isEmpty()) {
                        miface.onGranted();
                    } else {
                        miface.onDenied(deniedpermission);
                    }
                }
                break;
            default:
                break;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
