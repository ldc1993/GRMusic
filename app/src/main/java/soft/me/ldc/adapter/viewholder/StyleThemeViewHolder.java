package soft.me.ldc.adapter.viewholder;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.me.ldc.R;

/**
 * Created by liudi on 2018/1/11.
 */

public class StyleThemeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.mColor)
    public View mColor;
    @BindView(R.id.mColorText)
    public AppCompatTextView mColorText;
    @BindView(R.id.mColorCheckBox)
    public AppCompatCheckBox mColorCheckBox;

    public StyleThemeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
