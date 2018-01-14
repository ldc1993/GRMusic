package soft.me.ldc.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.view.GRSearchToolbar;
import soft.me.ldc.view.GRToastView;

public class QueryMusicActivity extends RootActivity {


    @BindView(R.id.mToolbar)
    GRSearchToolbar mToolbar;
    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.activity_query_music;
    }

    @Override
    protected void Main() {
        {
            mToolbar.setColor(R.color.colorAccent);
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setSearchHint("请输入关键字");
            mToolbar.setRightImg(R.mipmap.search_icon);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            mToolbar.setRightBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GRToastView.show(ctx, "" + mToolbar.getSearchEt().getText().toString(), Toast.LENGTH_SHORT);
                }
            });
        }


    }

    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


}
