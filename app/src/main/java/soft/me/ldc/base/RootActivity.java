package soft.me.ldc.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by liudi on 2018/1/10.
 */

public abstract class RootActivity extends AppCompatActivity {

    protected Context ctx;
    protected Activity act;
    protected FragmentManager fragmentManager;
    private Unbinder unbinder = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            NewCreate(savedInstanceState);
            setContentView(UI());
            unbinder = ButterKnife.bind(this);
            this.ctx = this;
            this.act = this;
            this.fragmentManager = this.getSupportFragmentManager();
            ViewId();
            Main();
        } catch (Exception e) {
            Error(e);
            e.printStackTrace();
        }

    }

    protected abstract void NewCreate(@Nullable Bundle savedInstanceState);

    protected abstract Integer UI();

    // TODO: 2018/1/10 绑定组件
    protected abstract void ViewId();

    // TODO: 2018/1/10 程序入口
    protected abstract void Main();

    protected abstract void Error(Exception e);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
