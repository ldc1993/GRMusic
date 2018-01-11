package soft.me.ldc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import soft.me.ldc.base.RootActivity;
import soft.me.ldc.view.GRSearchToolbar;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class LauncherUI extends RootActivity {

    AppCompatButton btn = null;
    GRToolbar toolbar = null;
    GRSearchToolbar searchtoolbar = null;

    @Override
    protected void NewUI(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.launcherui;
    }

    @Override
    protected void ViewId() {
        if (btn == null)
            btn = findViewById(R.id.btn);
        if (toolbar == null)
            toolbar = findViewById(R.id.toolbar);
        if (searchtoolbar == null)
            searchtoolbar = findViewById(R.id.search_toolbar);
    }

    @Override
    protected void Main() {
        {
            toolbar.setColorRes(R.color.green);
            toolbar.setTitleText("数字帝国");
            toolbar.setLeftImg(R.mipmap.ic_launcher);
            toolbar.setRightText("签到");
            toolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GRToastView.show(ctx, "哈喽1", Toast.LENGTH_SHORT);
                }
            });
            toolbar.setRightBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GRToastView.show(ctx, "哈喽2", Toast.LENGTH_SHORT);
                }
            });
            setSupportActionBar(toolbar);

        }

        {
            searchtoolbar.setColorRes(R.color.green);
            searchtoolbar.setSearchHint("请输入关键字");
            searchtoolbar.setLeftText("返回");
            searchtoolbar.setRightImg(R.mipmap.ic_launcher);
            searchtoolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GRToastView.show(ctx, "哈喽1", Toast.LENGTH_SHORT);
                }
            });
            searchtoolbar.setRightBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GRToastView.show(ctx, "哈喽2", Toast.LENGTH_SHORT);
                }
            });
            setSupportActionBar(searchtoolbar);

        }
        btn.setOnClickListener(new OnClickListener());
    }

    @Override
    protected void Error(Exception e) {

        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    class OnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn:
                    GRToastView.show(ctx, "测试数据", Toast.LENGTH_SHORT);
                    break;
            }
        }
    }


}
