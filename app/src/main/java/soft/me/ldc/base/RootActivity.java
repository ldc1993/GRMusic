package soft.me.ldc.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.me.ldc.service.PlayService;


/**
 * Created by liudi on 2018/1/10.
 */

public abstract class RootActivity extends AppCompatActivity {
    protected PlayService playService = null;
    protected Context ctx;
    protected Activity act;
    protected FragmentManager fragmentManager;
    private Unbinder unbinder = null;

    protected ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            playService = ((PlayService.ServiceBind) service).Service();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //音乐服务
            bindService(new Intent(this, PlayService.class), serviceConnection, BIND_AUTO_CREATE);
            NewCreate(savedInstanceState);
            setContentView(UI());
            unbinder = ButterKnife.bind(this);
            this.ctx = this;
            this.act = this;
            this.fragmentManager = this.getSupportFragmentManager();
            Main();
        } catch (Exception e) {
            Error(e);
            e.printStackTrace();
        }

    }

    protected abstract void NewCreate(@Nullable Bundle savedInstanceState);

    protected abstract Integer UI();

    // TODO: 2018/1/10 程序入口
    protected abstract void Main();

    protected abstract void Error(Exception e);

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
}
