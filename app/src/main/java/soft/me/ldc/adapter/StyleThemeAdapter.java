package soft.me.ldc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soft.me.ldc.R;
import soft.me.ldc.adapter.viewholder.StyleThemeViewHolder;
import soft.me.ldc.base.RootRecyclerViewAdapter;

/**
 * Created by liudi on 2018/1/11.
 */

public class StyleThemeAdapter extends RootRecyclerViewAdapter<StyleThemeViewHolder> implements View.OnClickListener {
    List<String> colors = null;
    List<String> texts = null;
    Map<Integer, Boolean> map = new HashMap<>();
    int isCheckedIndex = -1;
    Context ctx;
    //事件
    ItemListener listener = null;
    ItemCheckListener checkedlistener = null;

    //选择中的
    public void setIsChecked(int position, boolean isChecked) {
        map.put(position, isChecked);


    }

    //数据
    public void pushData(List<String> colors, List<String> texts) {
        this.colors = colors;
        this.texts = texts;
    }


    @Override
    public StyleThemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.ctx = parent.getContext();
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_style_theme_viewholder, parent, false);
        mainView.setOnClickListener(this);
        return new StyleThemeViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(StyleThemeViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.mColor.setBackgroundColor(Color.parseColor(colors.get(position)));
        holder.mColorText.setText(texts.get(position));
        // TODO: 2018/1/11 选择事件
        holder.mColorCheckBox.setOnCheckedChangeListener(new OnCheckedListener(position));
        if (map != null && map.containsKey(position)) {
            holder.mColorCheckBox.setChecked(true);
        } else {
            holder.mColorCheckBox.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return colors != null || texts != null ? colors.size() >= texts.size() ? texts.size() : colors.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (this.listener != null) {
            listener.onItemClick(v, (Integer) v.getTag());
        }

    }


    class OnCheckedListener implements CompoundButton.OnCheckedChangeListener {
        int position = 0;

        public OnCheckedListener(int position) {
            this.position = position;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                map.clear();
                map.put(position, true);
            } else {
                map.remove(position);
                if (map.size() == 0) {
                    isCheckedIndex = -1;
                }
            }
            if (checkedlistener != null) {
                checkedlistener.onItemCheckedClick(position, isChecked);
            }
            notifyDataSetChanged();

        }
    }

    // TODO: 2018/1/11 暴露接口
    public void setItemListener(ItemListener listener) {
        this.listener = listener;
    }

    // TODO: 2018/1/11 暴露接口
    public void setCheckedlistener(ItemCheckListener checkedlistener) {
        this.checkedlistener = checkedlistener;
    }

    // TODO: 2018/1/11 接口
    public interface ItemListener {
        void onItemClick(View view, int position);

    }

    // TODO: 2018/1/11  接口
    public interface ItemCheckListener {
        void onItemCheckedClick(int position, boolean checked);

    }
}
