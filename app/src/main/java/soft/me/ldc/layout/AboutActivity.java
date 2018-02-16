package soft.me.ldc.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import soft.me.ldc.BuildConfig;
import soft.me.ldc.R;
import soft.me.ldc.adapter.AboutListAdapter;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.view.GRToolbar;

public class AboutActivity extends RootMusicActivity {

    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mList)
    ListView mList;

    //
    AboutListAdapter aboutListAdapter = null;
    String[] titles = {"开发", "音乐", "天气数据", "版本号"};
    String[] txts = {"@数字帝国", "百度", "高德", "" + BuildConfig.VERSION_NAME};

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.activity_about;
    }

    @Override
    protected void Main() {
        {
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setTitleText("关于");
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setSupportActionBar(mToolbar);
        }
        {
            if (aboutListAdapter == null)
                aboutListAdapter = new AboutListAdapter(ctx);
            aboutListAdapter.pushData(Arrays.asList(titles), Arrays.asList(txts));
            mList.setAdapter(aboutListAdapter);

        }

    }

    //Map实例
    class dataMap {
        public HashMap put(Object key, Object value) {
            HashMap<String, String> map = new HashMap<>();
            map.put(String.valueOf(key), String.valueOf(value));
            return map;
        }
    }
}
