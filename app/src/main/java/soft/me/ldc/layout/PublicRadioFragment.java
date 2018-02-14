package soft.me.ldc.layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.PublicRadioStationAdapter;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.model.RadioStationBean;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicRadioFragment extends RootFragment {


    @BindView(R.id.mList)
    RecyclerView mList;

    //数据
    List<RadioStationBean.ResultBean.ChannellistBean> mData = null;

    public void pushData(List<RadioStationBean.ResultBean.ChannellistBean> mData) {
        this.mData = mData;
    }

    //
    StaggeredGridLayoutManager glm = null;
    PublicRadioStationAdapter publicRadioStationAdapter = null;

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        View mainView= inflater.inflate(R.layout.fragment_public_radio, null, false);
        mainView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        return mainView;
    }

    @Override
    protected void Init() throws Exception {

    }

    @Override
    protected void Main() throws Exception {
        {
            if (glm == null)
                glm = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
            if (publicRadioStationAdapter == null)
                publicRadioStationAdapter = new PublicRadioStationAdapter();
            if (mData != null)
                publicRadioStationAdapter.pushData(mData);
            else
                publicRadioStationAdapter.pushData(mData);
            publicRadioStationAdapter.setListener(new ItemListener());
            publicRadioStationAdapter.notifyDataSetChanged();
        }
        mList.setLayoutManager(glm);
        mList.setLayoutFrozen(true);
        mList.setHasFixedSize(true);
        mList.setAdapter(publicRadioStationAdapter);
    }


    //点击事件
    class ItemListener implements PublicRadioStationAdapter.OnItemListener {

        @Override
        public void itemClick(View view, RadioStationBean.ResultBean.ChannellistBean type) {
            try {
                Bundle bundle = new Bundle();
                bundle.putSerializable("type", type);
                Intent it = new Intent();
                it.setClass(ctx, RadioStationSongActivity.class);
                it.putExtras(bundle);
                startActivity(it);
            } catch (Exception e) {
                GRToastView.show(ctx, "错误", Toast.LENGTH_SHORT);
                e.printStackTrace();
            }
        }
    }
}
