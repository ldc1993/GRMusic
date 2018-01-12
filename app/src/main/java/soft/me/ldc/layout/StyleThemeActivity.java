package soft.me.ldc.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class StyleThemeActivity extends RootActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mList)
    RecyclerView mList;


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.activity_style_theme;
    }


    @Override
    protected void Main() {
        {
            mToolbar.setLeftText("返回");
            mToolbar.setTitleText("修改主题");
            mToolbar.setColorRes(R.color.colorPrimary);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setSupportActionBar(mToolbar);
        }

    }


    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


}
