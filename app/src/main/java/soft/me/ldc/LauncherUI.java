package soft.me.ldc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.view.GRSearchToolbar;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class LauncherUI extends RootActivity {

    @BindView(R.id.toolbar)
    GRToolbar toolbar;
    @BindView(R.id.search_toolbar)
    GRSearchToolbar searchToolbar;
    @BindView(R.id.btn)
    AppCompatButton btn;

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.launcherui;
    }

    @Override
    protected void ViewId() {

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
            searchToolbar.setColorRes(R.color.green);
            searchToolbar.setSearchHint("请输入关键字");
            searchToolbar.setLeftText("返回");
            searchToolbar.setRightImg(R.mipmap.ic_launcher);
            searchToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GRToastView.show(ctx, "哈喽1", Toast.LENGTH_SHORT);
                }
            });
            searchToolbar.setRightBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GRToastView.show(ctx, "哈喽2", Toast.LENGTH_SHORT);
                }
            });
            setSupportActionBar(searchToolbar);

        }

    }

    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    @OnClick({R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                GRToastView.show(ctx, "测试数据", Toast.LENGTH_SHORT);
                break;
        }
    }


}
