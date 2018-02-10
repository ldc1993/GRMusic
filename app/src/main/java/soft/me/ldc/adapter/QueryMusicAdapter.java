package soft.me.ldc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.QueryMusicViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;
import soft.me.ldc.model.QueryMusicBean;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by liudi on 2018/1/15.
 */

public class QueryMusicAdapter extends RootRecyclerViewAdapter<QueryMusicViewHolder> implements View.OnClickListener {
    List<QueryMusicBean.ResultBean.SongInfoBean.SongListBean> mData;
    OnItemListener listener = null;
    Context ctx = null;

    public void pushData(List<QueryMusicBean.ResultBean.SongInfoBean.SongListBean> mData) {
        this.mData = mData;
    }

    @Override
    public QueryMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_music_list_item, parent, false);
        this.ctx = parent.getContext();
        mainView.setOnClickListener(this);
        return new QueryMusicViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(QueryMusicViewHolder holder, int position) {
        setAnimator(holder.itemView);
        QueryMusicBean.ResultBean.SongInfoBean.SongListBean data = mData.get(position);
        holder.itemView.setTag(data);
        //设置
        holder.mTitle.setText(data.title + "");
        holder.mAuthor.setText("作者 - " + data.author + "");
        holder.mAlbum.setText("专辑 - " + data.album_title + "");
        if (StringUtil.isNotBlank(data.pic_small))
            Picasso.with(ctx).load(data.pic_small).resize(56, 56).centerInside().into(holder.mIcon);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            this.listener.ItemClick(v, (QueryMusicBean.ResultBean.SongInfoBean.SongListBean) v.getTag());
        }
    }

    //接口
    public interface OnItemListener {
        void ItemClick(View view, QueryMusicBean.ResultBean.SongInfoBean.SongListBean type);
    }

    //暴露接口
    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }
}
