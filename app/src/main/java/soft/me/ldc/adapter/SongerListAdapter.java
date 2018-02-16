package soft.me.ldc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.SongerListViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;
import soft.me.ldc.model.SongerListBean;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by liudi on 2018/2/16.
 */

public class SongerListAdapter extends RootRecyclerViewAdapter<SongerListViewHolder> implements View.OnClickListener {
    SongerListBean mData = null;
    OnItemListener listener = null;
    Context ctx = null;

    public void pushData(SongerListBean beans) {
        this.mData = beans;
    }

    @Override
    public SongerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.songer_list_item, parent, false);
        this.ctx = parent.getContext();
        mainView.setOnClickListener(this);
        return new SongerListViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(SongerListViewHolder holder, int position) {
        SongerListBean.ArtistBean data = mData.artist.get(position);
        if (data != null) {
            holder.itemView.setTag(data);
            if (StringUtil.isNotBlank(data.avatar_big))
                Picasso.with(ctx).load(data.avatar_big).resize(500, 500).centerInside().into(holder.mIcon);
            if (StringUtil.isNotBlank(data.name))
                holder.mTxt.setText(data.name);
        }
    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.artist == null) ? 0 : mData.artist.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onItem(v, (SongerListBean) v.getTag());
    }

    public interface OnItemListener {
        void onItem(View v, SongerListBean type);
    }

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }
}
