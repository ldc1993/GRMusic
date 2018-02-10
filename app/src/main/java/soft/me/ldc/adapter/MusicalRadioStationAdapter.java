package soft.me.ldc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.MusicalRadioStationViewHolder;
import soft.me.ldc.adapter.viewholder.PublicRadioStationViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;
import soft.me.ldc.model.RadioStationBean;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by liudi on 2018/1/15.
 */

public class MusicalRadioStationAdapter extends RootRecyclerViewAdapter<MusicalRadioStationViewHolder> implements View.OnClickListener {
    List<RadioStationBean.ResultBean.ChannellistBean> mData = null;
    Context ctx = null;
    OnItemListener listener = null;


    public void pushData(List<RadioStationBean.ResultBean.ChannellistBean> mData) {
        this.mData = mData;
    }

    @Override
    public MusicalRadioStationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.ctx = parent.getContext();
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_station_list_item, parent, false);
        mainView.setOnClickListener(this);
        return new MusicalRadioStationViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(MusicalRadioStationViewHolder holder, int position) {
        setAnimator(holder.itemView);
        RadioStationBean.ResultBean.ChannellistBean data = mData.get(position);
        holder.itemView.setTag(data);
        holder.mName.setText("" + data.name);
        holder.mCate_sname.setText(null);
        if (StringUtil.isNotBlank(data.avatar))
            Picasso.with(ctx).load(data.avatar).resize(500, 500).centerCrop().into(holder.mIcon);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (this.listener != null)
            this.listener.itemClick(v, (RadioStationBean.ResultBean.ChannellistBean) v.getTag());
    }

    //接口
    public interface OnItemListener {
        void itemClick(View view, RadioStationBean.ResultBean.ChannellistBean type);
    }
    //暴露接口

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }
}
