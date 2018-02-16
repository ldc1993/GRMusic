package soft.me.ldc.adapter.viewholder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.me.ldc.R;

/**
 * Created by liudi on 2018/2/16.
 */

public class SongerListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.mIcon)
    public AppCompatImageView mIcon;
    @BindView(R.id.mText)
    public AppCompatTextView mTxt;

    public SongerListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
