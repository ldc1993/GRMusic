package soft.me.ldc.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;

import soft.me.ldc.R;

/**
 * Created by liudi on 2018/2/15.
 */

public class AboutListAdapter extends BaseAdapter {
    Context ctx = null;
    private List<String> mTitles = null;
    private List<String> mTxts = null;

    public AboutListAdapter(Context ctx) {
        this.ctx = ctx;
    }

    //数据
    public void pushData(List<String> mTitles, List<String> mTxts) {
        this.mTitles = mTitles;
        this.mTxts = mTxts;
    }

    @Override
    public int getCount() {
        return mTitles == null || mTxts == null ? 0 : mTitles.size() > mTxts.size() ? mTxts.size() : mTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewItem viewItem = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.activity_about_item, null, false);
            viewItem = new ViewItem();
            viewItem.itemTitle = convertView.findViewById(R.id.itemTitle);
            viewItem.itemTxt = convertView.findViewById(R.id.itemTxt);
            convertView.setTag(viewItem);
        } else {
            viewItem = (ViewItem) convertView.getTag();
        }
        viewItem.itemTitle.setText(mTitles.get(position) + " :");
        viewItem.itemTxt.setText(mTxts.get(position));


        return convertView;
    }

    //界面
    static class ViewItem {
        public AppCompatTextView itemTitle = null;
        public AppCompatTextView itemTxt = null;
    }
}
