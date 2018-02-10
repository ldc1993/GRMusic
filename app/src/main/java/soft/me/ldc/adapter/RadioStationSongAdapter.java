package soft.me.ldc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.RadioStationSongViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;
import soft.me.ldc.model.RadioStationSongBean;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by liudi on 2018/1/15.
 */

public class RadioStationSongAdapter extends RootRecyclerViewAdapter<RadioStationSongViewHolder> implements View.OnClickListener {
    List<RadioStationSongBean.ResultBean.SonglistBean> mData = null;
    Context ctx = null;
    OnItemListener listener = null;


    public void pushData(List<RadioStationSongBean.ResultBean.SonglistBean> mData) {
        this.mData = mData;
    }

    @Override
    public RadioStationSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.ctx = parent.getContext();
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_station_song_list_item, parent, false);
        mainView.setOnClickListener(this);
        return new RadioStationSongViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(RadioStationSongViewHolder holder, int position) {
        setAnimator(holder.itemView);
        RadioStationSongBean.ResultBean.SonglistBean data = mData.get(position);
        if (data != null && StringUtil.isNotBlank(data.title)) {
            holder.itemView.setTag(data);
            holder.mTitle.setText("" + data.title);
            holder.mArtist.setText("" + data.artist);
            if (StringUtil.isNotBlank(data.thumb))
                Picasso.with(ctx).load(data.thumb).resize(300, 300).centerCrop().into(holder.mIcon);

        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (this.listener != null)
            this.listener.itemClick(v, (RadioStationSongBean.ResultBean.SonglistBean) v.getTag());
    }

    //接口
    public interface OnItemListener {
        void itemClick(View view, RadioStationSongBean.ResultBean.SonglistBean type);
    }
    //暴露接口

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }
}
