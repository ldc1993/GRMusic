package soft.me.ldc.layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.model.RadioStationBean;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;

/**
 * 电台
 * A simple {@link Fragment} subclass.
 */
public class RadioStationFragment extends RootFragment {


    @BindView(R.id.mViewContent)
    LinearLayoutCompat mViewContent;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    //
    Gson gson = null;
    //
    GRLoadDialog loadDialog = null;
    //持久任务
    RefreshTask refreshTask = null;
    //消息
    Message msg = null;
    //数据
    volatile List<RadioStationBean.ResultBean.ChannellistBean> public_data = null;//公共频道
    volatile List<RadioStationBean.ResultBean.ChannellistBean> musical_data = null;//音乐人频道

    static final int REFRESHCODE = 0x000;//刷新
    static final int UPDATEVIEWCODE = 0x001;//更新数据
    static final int NODATACODE = 0x002;//没有数据
    static final int ERRORCODE = 0x003;//错误
    @BindView(R.id.mSelect)
    BottomNavigationView mSelect;
    Unbinder unbinder;
    private Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESHCODE:
                    RunRefreshTask();
                    break;
                case UPDATEVIEWCODE:
                    RadioStationBean data = (RadioStationBean) msg.obj;
                    if (data != null) {
                        if (data.result != null && data.result.size() > 0 && data.result.get(0).channellist != null && data.result.get(0).channellist.size() > 0) {
                            PublicRadioFragment fragment = new PublicRadioFragment();
                            fragment.pushData(data.result.get(0).channellist);
                            SwitchPager(fragment);
                            if (public_data != null)
                                public_data.clear();
                            if (musical_data != null)
                                musical_data.clear();
                            public_data = data.result.get(0).channellist;
                            musical_data = data.result.get(1).channellist;
                        } else {
                            dkhandler.sendEmptyMessage(NODATACODE);
                        }
                    } else {
                        dkhandler.sendEmptyMessage(NODATACODE);
                    }
                    break;
                case NODATACODE:
                    GRToastView.show(ctx, "没有数据!", Toast.LENGTH_SHORT);
                    break;
                case ERRORCODE:
                    GRToastView.show(ctx, "加载错误!", Toast.LENGTH_SHORT);
                    break;
            }
        }
    };

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        return inflater.inflate(R.layout.fragment_radiostation, null, false);
    }

    @Override
    protected void Init() throws Exception {
        smartrefreshlayout.setOnRefreshListener(new RefreshListener());
    }

    @Override
    protected void Main() throws Exception {
        mSelect.setOnNavigationItemSelectedListener(new SelectListener());
        //加载数据
        dkhandler.sendEmptyMessage(REFRESHCODE);
    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    // TODO: 2018/1/17 页面切换
    private void SwitchPager(Fragment fragment) {
        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.mViewContent, fragment).commitNow();
    }

    //选择页面
    class SelectListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        volatile boolean callback = false;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.radio_item1:
                    callback = true;
                    PublicRadioFragment fragment1 = new PublicRadioFragment();
                    fragment1.pushData(public_data);
                    SwitchPager(fragment1);
                    break;
                case R.id.radio_item2:
                    callback = true;
                    MusicalRadioFragment fragment2 = new MusicalRadioFragment();
                    fragment2.pushData(musical_data);
                    SwitchPager(fragment2);
                    break;
            }
            return callback;
        }
    }

    //刷新事件
    class RefreshListener implements OnRefreshListener {

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            dkhandler.sendEmptyMessage(REFRESHCODE);
            refreshlayout.finishRefresh(2000);
        }
    }

    //执行任务
    private void RunRefreshTask() {
        if (refreshTask != null && !refreshTask.isCancelled()) {
            refreshTask.cancel(true);
        }
        refreshTask = new RefreshTask();
        refreshTask.execute();
    }

    //加载任务
    class RefreshTask extends AsyncTask<Void, Void, RadioStationBean> {
        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("数据加载中···", true);
        }

        @Override
        protected RadioStationBean doInBackground(Void... voids) {

            RadioStationBean radioStationBean = null;
            try {
                if (gson == null)
                    gson = new Gson();
                String str = HttpService.Instance(ctx).RadioStation();
                radioStationBean = gson.fromJson(str, RadioStationBean.class);

            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return radioStationBean;
        }

        @Override
        protected void onPostExecute(RadioStationBean result) {
            msg = new Message();
            if (result != null) {
                msg.what = UPDATEVIEWCODE;
                msg.obj = result;
            } else {
                msg.what = NODATACODE;
            }
            dkhandler.sendMessage(msg);
            loadDialog.dismiss();
        }
    }


}
