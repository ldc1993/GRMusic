package soft.me.ldc.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.me.ldc.R;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.view.GRToolbar;

public class StyleActivity extends RootActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.activity_style;
    }


    @Override
    protected void Main() {

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

    @Override
    protected void Error(Exception e) {

    }


}
