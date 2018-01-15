package soft.me.ldc.layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.MusicTypeListAdapter;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.model.MusicTypeBean;
import soft.me.ldc.view.GRSearchView;
import soft.me.ldc.view.GRToastView;


public class MusicFragment extends RootFragment {

    @BindView(R.id.searchView)
    GRSearchView searchView;
    @BindView(R.id.musicTypeList)
    RecyclerView musicTypeList;

    MusicTypeListAdapter musicTypeListAdapter = null;
    StaggeredGridLayoutManager sglm = null;

    //
    Bundle bundle = null;

    //数据
    List<MusicTypeBean> mData = null;


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {

        return inflater.inflate(R.layout.fragment_music, null, false);
    }

    @Override
    protected void Init() throws Exception {
        searchView.setHint("请输入关键字");
        searchView.setHintColor("#ffffff");
        searchView.setSearchBtnListener(new SearchListener());
        initData();//初始化数据
    }

    @Override
    protected void Main() throws Exception {
        if (sglm == null)
            sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        if (musicTypeListAdapter == null)
            musicTypeListAdapter = new MusicTypeListAdapter();
        musicTypeListAdapter.pushData(mData);
        musicTypeListAdapter.setListener(new OnItemListener());
        musicTypeList.setHasFixedSize(true);
        musicTypeList.setLayoutFrozen(true);
        musicTypeList.setLayoutManager(sglm);
        musicTypeList.setAdapter(musicTypeListAdapter);

    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    // TODO: 2018/1/12 搜索事件
    class SearchListener implements GRSearchView.onSearchListener {

        @Override
        public void onSearchClick(View view, String key) {
            Intent it = new Intent();
            it.setClass(ctx, QueryMusicActivity.class);
            startActivity(it);
        }
    }

    // TODO: 2018/1/13 初始化数据
    private void initData() {
        if (mData == null)
            mData = new ArrayList<>();
        mData.clear();//清空数据
        mData.add(new MusicTypeBean("1", "新歌", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("1", "热歌", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("11", "摇滚", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("12", "爵士", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("16", "流行", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("21", "欧美金曲", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("22", "金典老歌", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("23", "情歌对唱", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("24", "影视金曲", R.mipmap.ic_launcher));
        mData.add(new MusicTypeBean("25", "网络歌曲", R.mipmap.ic_launcher));

    }

    // TODO: 2018/1/13 item点击事件
    class OnItemListener implements MusicTypeListAdapter.OnItemListener {

        @Override
        public void onItem(View view, MusicTypeBean type) {
            try {
                if (bundle == null)
                    bundle = new Bundle();
                bundle.putSerializable("type", type);
                Intent it = new Intent();
                it.setClass(ctx, MusicListActivity.class);
                it.putExtras(bundle);
                startActivity(it);
            } catch (Exception e) {
                GRToastView.show(ctx, "点击无效", Toast.LENGTH_SHORT);
                e.printStackTrace();
            }

        }
    }
}
