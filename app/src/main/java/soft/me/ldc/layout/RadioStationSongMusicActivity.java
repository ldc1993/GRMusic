package soft.me.ldc.layout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.RadioStationSongAdapter;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.model.RadioStationBean;
import soft.me.ldc.model.RadioStationSongBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.task.PlayMusicTask;
import soft.me.ldc.common.pool.MultiThreadPool;
import soft.me.ldc.utils.NetUtil;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class RadioStationSongMusicActivity extends RootMusicActivity {


    @BindView(R.id.mtoolbar)
    GRToolbar mtoolbar;
    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    //
    RadioStationBean.ResultBean.ChannellistBean data = null;
    //
    int PageNo = 0;
    //持久任务
    RefreshTask refreshTask = null;
    LoadmoreTask loadmoreTask = null;
    //
    GRLoadDialog loadDialog = null;
    //
    Gson gson = null;
    //消息
    Message msg = null;
    //
    LinearLayoutManager llm = null;
    RadioStationSongAdapter radioStationSongAdapter = null;


    static final int REFRESHCODE = 0x000;//刷新
    static final int LOADMORECODE = 0x001;//刷新
    static final int UPDATEVIEWCODE = 0x002;//更新数据
    static final int NODATACODE = 0x003;//没有数据
    static final int ERRORCODE = 0x004;//错误
    private Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESHCODE:
                    RunRefreshTask(data.ch_name);
                    break;
                case LOADMORECODE:
                    RunLoadmoreTask(data.ch_name);
                    break;
                case UPDATEVIEWCODE:
                    RadioStationSongBean data = (RadioStationSongBean) msg.obj;
                    if (data != null && data.result != null) {
                        if (data.result.songlist != null && data.result.songlist.size() > 0) {
                            if (radioStationSongAdapter == null)
                                return;
                            radioStationSongAdapter.pushData(data.result.songlist);
                            radioStationSongAdapter.notifyDataSetChanged();
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
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        ActivityList.addActivity(this);
        data = (RadioStationBean.ResultBean.ChannellistBean) getIntent().getSerializableExtra("type");
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_radio_station_song;
    }

    @Override
    protected void Main() {
        {
            mtoolbar.setLeftImg(R.mipmap.back_icon);
            mtoolbar.setTitleText(data.name);
            mtoolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
        {
            smartrefreshlayout.setOnLoadmoreListener(new RefreshListener());
            smartrefreshlayout.setOnRefreshListener(new RefreshListener());
            smartrefreshlayout.setEnableAutoLoadmore(false);
        }
        if (gson == null)
            gson = new Gson();
        {
            if (llm == null)
                llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
            if (radioStationSongAdapter == null)
                radioStationSongAdapter = new RadioStationSongAdapter();
            radioStationSongAdapter.pushData(null);
            radioStationSongAdapter.setListener(new ItemListener());
            mList.setLayoutManager(llm);
            mList.setLayoutFrozen(true);
            mList.setHasFixedSize(true);
            mList.setAdapter(radioStationSongAdapter);
        }
        //加载数据
        dkhandler.sendEmptyMessage(REFRESHCODE);

    }


    //刷新事件
    class RefreshListener implements OnRefreshListener, OnLoadmoreListener {

        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {
            dkhandler.sendEmptyMessage(LOADMORECODE);
            refreshlayout.finishLoadmore(2000);
        }

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            dkhandler.sendEmptyMessage(REFRESHCODE);
            refreshlayout.finishRefresh(2000);
        }
    }

    class ItemListener implements RadioStationSongAdapter.OnItemListener {

        @Override
        public void itemClick(View view, RadioStationSongBean.ResultBean.SonglistBean type) {
            if (NetUtil.isAvailable(ctx)) {
                try {
                    PlayMusicTask playMusicTask = PlayMusicTask.Instance(ctx, 1);
                    playMusicTask.pushData(type.songid);
                    playMusicTask.pushPlayState(true);
                    MultiThreadPool.newInsance().pushThread(playMusicTask);
                } catch (Exception e) {
                    GRToastView.show(ctx, "错误!", Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }
            } else {
                NetUtil.NetSetting(ctx);
            }
        }
    }


    //执行任务
    private void RunRefreshTask(String qry) {
        if (refreshTask != null && !refreshTask.isCancelled()) {
            refreshTask.cancel(true);
        }
        refreshTask = new RefreshTask();
        refreshTask.pushQry(qry);
        refreshTask.execute();
    }

    //执行任务
    private void RunLoadmoreTask(String qry) {
        if (loadmoreTask != null && !loadmoreTask.isCancelled()) {
            loadmoreTask.cancel(true);
        }
        loadmoreTask = new LoadmoreTask();
        loadmoreTask.pushQry(qry);
        loadmoreTask.execute();
    }

    //加载
    class RefreshTask extends AsyncTask<Void, Void, RadioStationSongBean> {
        String qry = "";

        public void pushQry(String qry) {
            this.qry = qry;
        }

        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("数据加载中···", true);
        }

        @Override
        protected RadioStationSongBean doInBackground(Void... voids) {
            RadioStationSongBean radioStationSongBean = null;
            try {
                if (gson == null)
                    gson = new Gson();
                PageNo = 0;
                String str = HttpService.Instance(ctx).RadioStationSong(qry, PageNo + "", 20 + "");
                radioStationSongBean = gson.fromJson(str, RadioStationSongBean.class);

            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }

            return radioStationSongBean;
        }

        @Override
        protected void onPostExecute(RadioStationSongBean result) {
            msg = new Message();
            if (result != null) {
                msg.what = UPDATEVIEWCODE;
                msg.obj = result;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);

        }
    }

    //加载更多
    class LoadmoreTask extends AsyncTask<Void, Void, RadioStationSongBean> {
        String qry = "";

        public void pushQry(String qry) {
            this.qry = qry;
        }

        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("数据加载中···", true);
        }

        @Override
        protected RadioStationSongBean doInBackground(Void... voids) {
            RadioStationSongBean radioStationSongBean = null;
            try {
                if (gson == null)
                    gson = new Gson();
                PageNo++;
                String str = HttpService.Instance(ctx).RadioStationSong(qry, PageNo + "", 20 + "");
                radioStationSongBean = gson.fromJson(str, RadioStationSongBean.class);

            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }

            return radioStationSongBean;
        }

        @Override
        protected void onPostExecute(RadioStationSongBean result) {
            msg = new Message();
            if (result != null) {
                msg.what = UPDATEVIEWCODE;
                msg.obj = result;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);
        }
    }
}
