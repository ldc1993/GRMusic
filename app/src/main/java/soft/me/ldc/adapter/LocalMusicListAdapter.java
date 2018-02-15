package soft.me.ldc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.LocalMusicListViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;
import soft.me.ldc.model.LocalMusicBean;

/**
 * Created by liudi on 2018/2/12.
 */

public class LocalMusicListAdapter extends RootRecyclerViewAdapter<LocalMusicListViewHolder> implements View.OnClickListener {
    List<LocalMusicBean> localMusicBeans = null;
    OnItemListener itemListener = null;

    public void pushData(List<LocalMusicBean> localMusicBeans) {
        this.localMusicBeans = localMusicBeans;
    }

    @Override
    public LocalMusicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_music_list_item, parent, false);
        mainView.setOnClickListener(this);
        return new LocalMusicListViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(LocalMusicListViewHolder holder, int position) {
        LocalMusicBean bean = localMusicBeans.get(position);
        setAnimator(holder.itemView);
        if (bean != null) {
            holder.itemView.setTag(bean);
            holder.mTitle.setText(bean.title);
            holder.mAuthor.setText(bean.author);
            holder.mArtist.setText("未知");
        }

    }

    @Override
    public int getItemCount() {
        return localMusicBeans == null ? 0 : localMusicBeans.size();
    }

    @Override
    public void onClick(View v) {
        if (itemListener != null)
            itemListener.onItem(v, (LocalMusicBean) v.getTag());

    }

    //接口
    public interface OnItemListener {
        void onItem(View view, LocalMusicBean type);
    }

    public void setItemListener(OnItemListener itemListener) {
        this.itemListener = itemListener;
    }
}
