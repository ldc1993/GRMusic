package soft.me.ldc.layout;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.RadioStationAdapter;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.model.RadioStationBean;
import soft.me.ldc.service.MusicService;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;

/**
 * 电台
 * A simple {@link Fragment} subclass.
 */
public class RadioStationFragment extends RootFragment {


    @BindView(R.id.mList)
    RecyclerView mList;
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
    //
    StaggeredGridLayoutManager sglm = null;
    RadioStationAdapter radioStationAdapter = null;

    static final int REFRESHCODE = 0x000;//刷新
    static final int UPDATEVIEWCODE = 0x001;//更新数据
    static final int NODATACODE = 0x002;//没有数据
    static final int ERRORCODE = 0x003;//错误
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
                            if (radioStationAdapter == null)
                                return;
                            radioStationAdapter.pushData(data.result.get(0).channellist);
                            radioStationAdapter.notifyDataSetChanged();
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
        {
            if (sglm == null)
                sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            if (radioStationAdapter == null)
                radioStationAdapter = new RadioStationAdapter();
            radioStationAdapter.pushData(null);
            radioStationAdapter.setListener(new ItemListener());
        }
        mList.setLayoutManager(sglm);
        mList.setLayoutFrozen(true);
        mList.setHasFixedSize(true);
        mList.setAdapter(radioStationAdapter);
        //加载数据
        dkhandler.sendEmptyMessage(REFRESHCODE);
    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


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
                String str = MusicService.Instance(ctx).RadioStation();
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

    //点击事件
    class ItemListener implements RadioStationAdapter.OnItemListener {

        @Override
        public void itemClick(View view, RadioStationBean.ResultBean.ChannellistBean type) {
            try {
                Bundle bundle = new Bundle();
                bundle.putSerializable("type", type);
                Intent it = new Intent();
                it.setClass(ctx, RadioStationSongActivity.class);
                it.putExtras(bundle);
                startActivity(it);
            } catch (Exception e) {
                GRToastView.show(ctx, "错误", Toast.LENGTH_SHORT);
                e.printStackTrace();
            }
        }
    }

}
