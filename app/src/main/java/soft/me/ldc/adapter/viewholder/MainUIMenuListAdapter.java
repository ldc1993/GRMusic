package soft.me.ldc.adapter.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import soft.me.ldc.MainUI;
import soft.me.ldc.R;
import soft.me.ldc.base.RootRecyclerViewAdapter;

/**
 * Created by liudi on 2018/2/15.
 */

public class MainUIMenuListAdapter extends RootRecyclerViewAdapter<MainUIMenuListItemViewHolder> implements View.OnClickListener {
    private List<String> itemTxt = null;
    private onItemListener listener = null;

    public void pushData(List<String> itemTxt) {
        this.itemTxt = itemTxt;
    }

    @Override
    public MainUIMenuListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_ui_menulist_item, parent, false);
        mainView.setOnClickListener(this);
        return new MainUIMenuListItemViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(MainUIMenuListItemViewHolder holder, int position) {
        holder.itemView.setTag(position);
        String str = itemTxt.get(position);
        holder.itemTxt.setText(str);

    }

    @Override
    public int getItemCount() {
        return itemTxt == null ? 0 : itemTxt.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.Click(v, (Integer) v.getTag());

    }

    public interface onItemListener {
        void Click(View v, int position);
    }

    public void setOnItemListener(onItemListener listener) {
        this.listener = listener;
    }
}
