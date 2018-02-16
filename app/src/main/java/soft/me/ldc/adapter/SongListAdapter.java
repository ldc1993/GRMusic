package soft.me.ldc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.SongListViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;
import soft.me.ldc.model.SongListBean;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by liudi on 2018/2/16.
 */

public class SongListAdapter extends RootRecyclerViewAdapter<SongListViewHolder> implements View.OnClickListener {
    SongListBean mData = null;
    Context ctx = null;
    OnItemListener listener = null;

    public void pushData(SongListBean mData) {
        this.mData = mData;
    }

    @Override
    public SongListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.ctx = parent.getContext();
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, null, false);
        mainView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mainView.setOnClickListener(this);
        return new SongListViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(SongListViewHolder holder, int position) {
        SongListBean.SonglistBean bean = mData.songlist.get(position);
        setAnimator(holder.itemView);
        if (bean != null) {
            holder.itemView.setTag(bean);
            if (StringUtil.isNotBlank(bean.pic_s500))
                Picasso.with(ctx).load(bean.pic_s500).resize(500, 500).centerInside().into(holder.mIcon);
            if (StringUtil.isNotBlank(bean.title))
                holder.mTitle.setText(bean.title);
            if (StringUtil.isNotBlank(bean.author))
                holder.mAuthor.setText(bean.author);
        }

    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.songlist == null) ? 0 : mData.songlist.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.OnItem(v, (SongListBean.SonglistBean) v.getTag());

    }

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }

    public interface OnItemListener {
        void OnItem(View v, SongListBean.SonglistBean type);
    }
}
