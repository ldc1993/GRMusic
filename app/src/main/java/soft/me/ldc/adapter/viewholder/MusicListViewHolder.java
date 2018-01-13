package soft.me.ldc.adapter.viewholder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.security.PublicKey;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import soft.me.ldc.R;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class MusicListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.mIcon)
    public AppCompatImageView mIcon;
    @BindView(R.id.mTitle)
    public AppCompatTextView mTitle;
    @BindView(R.id.mAuthor)
    public AppCompatTextView mAuthor;
    @BindView(R.id.mCountry)
    public AppCompatTextView mCountry;
    @BindView(R.id.mCompany)
    public AppCompatTextView mCompany;
    @BindView(R.id.mLanguage)
    public AppCompatTextView mLanguage;

    public MusicListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
