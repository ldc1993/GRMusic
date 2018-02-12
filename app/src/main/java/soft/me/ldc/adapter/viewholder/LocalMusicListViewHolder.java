package soft.me.ldc.adapter.viewholder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.me.ldc.R;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class LocalMusicListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.mIcon)
    public AppCompatImageView mIcon;
    @BindView(R.id.mTitle)
    public AppCompatTextView mTitle;
    @BindView(R.id.mAuthor)
    public AppCompatTextView mAuthor;
    @BindView(R.id.mArtist)
    public AppCompatTextView mArtist;

    public LocalMusicListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
