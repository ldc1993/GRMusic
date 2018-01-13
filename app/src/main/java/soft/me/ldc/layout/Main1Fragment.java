package soft.me.ldc.layout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.view.GRSearchView;
import soft.me.ldc.view.GRToastView;


public class Main1Fragment extends RootFragment {

    @BindView(R.id.searchView)
    GRSearchView searchView;

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {

        return inflater.inflate(R.layout.fragment_main1, null, false);
    }

    @Override
    protected void Init() throws Exception {
        searchView.setHint("请输入关键字");
        searchView.setHintColor("#808080");
        searchView.setSearchBtnListener(new SearchListener());
    }

    @Override
    protected void Main() throws Exception {

    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    // TODO: 2018/1/12 搜索事件
    class SearchListener implements GRSearchView.onSearchListener {

        @Override
        public void onSearchClick(View view, String key) {
            GRToastView.show(ctx, "" + key, Toast.LENGTH_SHORT);
        }
    }
}
