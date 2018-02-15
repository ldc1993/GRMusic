package soft.me.ldc.adapter.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.me.ldc.R;

/**
 * Created by liudi on 2018/2/15.
 */

public class MainUIMenuListItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemTxt)
    AppCompatTextView itemTxt;

    public MainUIMenuListItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
