package soft.me.ldc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.MusicTypeListViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;
import soft.me.ldc.model.MusicType;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class MusicTypeListAdapter extends RootRecyclerViewAdapter<MusicTypeListViewHolder> implements View.OnClickListener {
    List<MusicType> mData = null;
    OnItemListener listener = null;

    public void pushData(List<MusicType> mData) {
        this.mData = mData;
    }

    @Override
    public MusicTypeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_type_list_item, parent, false);
        mainView.setOnClickListener(this);
        return new MusicTypeListViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(MusicTypeListViewHolder holder, int position) {

        MusicType type = mData.get(position);
        holder.itemView.setTag(type);
        setAnimator(holder.itemView);
        holder.icon.setImageResource(type.typeIcon);
        holder.text.setText(type.typeName);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (this.listener != null) {
            this.listener.onItem(v, (MusicType) v.getTag());
        }

    }

    //接口
    public interface OnItemListener {
        void onItem(View view, MusicType type);
    }

    //暴露接口
    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }
}
