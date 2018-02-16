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
import soft.me.ldc.adapter.SongListAdapter;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.common.pool.MultiThreadPool;
import soft.me.ldc.model.SongListBean;
import soft.me.ldc.model.SongerListBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.task.PlayMusicTask;
import soft.me.ldc.utils.NetUtil;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class SongListActivity extends RootMusicActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    //接收数据
    SongerListBean.ArtistBean mData = null;

    //
    GRLoadDialog loadDialog = null;
    //
    volatile int pageNo = 0;
    final int limitNo = 15;
    //消息
    Message msg = null;
    //
    Bundle bundle = null;
    //
    Gson gson = null;
    //
    RefreshSongListTask refreshSongListTask = null;
    LoadmoreSongListTask loadmoreSongListTask = null;
    //
    SongListAdapter songListAdapter = null;
    LinearLayoutManager llm = null;
    //
    final static int REFRESHCODE = 0x001;
    final static int LOADMORECODE = 0x002;
    final static int UPDATECODE = 0x003;
    final static int ERRORCODE = 0x004;
    final static int NODATACODE = 0x005;
    Handler dkhandlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String tinguid = "";
            switch (msg.what) {
                case REFRESHCODE:
                    tinguid = (String) msg.obj;
                    RunRefreshSongListTask(tinguid);
                    break;
                case LOADMORECODE:
                    tinguid = (String) msg.obj;
                    RunLoadmoreSongListTask(tinguid);
                    break;
                case UPDATECODE:
                    SongListBean data = (SongListBean) msg.obj;
                    if (data != null) {
                        if (songListAdapter == null)
                            songListAdapter = new SongListAdapter();
                        songListAdapter.pushData(data);
                        songListAdapter.notifyDataSetChanged();
                        mList.smoothScrollToPosition(0);
                    } else {
                        dkhandlder.sendEmptyMessage(NODATACODE);
                    }


                    break;
                case ERRORCODE:
                    GRToastView.show(ctx, "请求失败~", Toast.LENGTH_SHORT);
                    break;
                case NODATACODE:
                    GRToastView.show(ctx, "没有数据~", Toast.LENGTH_SHORT);
                    break;
            }


        }
    };

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        ActivityList.addActivity(this);
        mData = (SongerListBean.ArtistBean) getIntent().getExtras().getSerializable("songer");

    }

    @Override
    protected Integer UI() {
        return R.layout.activity_song_list;
    }

    @Override
    protected void Main() {
        if (mData == null)
            finish();
        {
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setTitleText(mData.name);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setSupportActionBar(mToolbar);
        }
        {
            smartrefreshlayout.setEnableAutoLoadmore(false);
            smartrefreshlayout.setOnRefreshListener(new OnRefreshLister());
            smartrefreshlayout.setOnLoadmoreListener(new OnRefreshLister());
        }

        {
            if (llm == null)
                llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
            if (songListAdapter == null)
                songListAdapter = new SongListAdapter();
            songListAdapter.pushData(null);
            songListAdapter.setListener(new OnItemListener());


            mList.setLayoutManager(llm);
            mList.setLayoutFrozen(true);
            mList.setHasFixedSize(true);
            mList.setAdapter(songListAdapter);


            //初始化数据
            dkhandlder.sendMessage(dkhandlder.obtainMessage(REFRESHCODE, mData.ting_uid));
        }

    }

    // TODO: 2018/2/16 Item 点击事件
    class OnItemListener implements SongListAdapter.OnItemListener {

        @Override
        public void OnItem(View v, SongListBean.SonglistBean type) {
            if (NetUtil.isAvailable(ctx)) {
                try {
                    PlayMusicTask playMusicTask = PlayMusicTask.Instance(ctx, 1);
                    playMusicTask.pushData(type.song_id);
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

    // TODO: 2018/2/16 刷新
    class OnRefreshLister implements OnRefreshListener, OnLoadmoreListener {

        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {
            dkhandlder.sendMessage(dkhandlder.obtainMessage(LOADMORECODE, mData.ting_uid));
            refreshlayout.finishLoadmore(2000);


        }

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            dkhandlder.sendMessage(dkhandlder.obtainMessage(REFRESHCODE, mData.ting_uid));
            refreshlayout.finishRefresh(2000);
        }
    }

    private void RunRefreshSongListTask(String tinguid) {
        if (refreshSongListTask != null && !refreshSongListTask.isCancelled()) {
            refreshSongListTask.cancel(true);
        }
        refreshSongListTask = new RefreshSongListTask();
        refreshSongListTask.pushData(tinguid);
        refreshSongListTask.execute();
    }

    private void RunLoadmoreSongListTask(String tinguid) {
        if (loadmoreSongListTask != null && !loadmoreSongListTask.isCancelled()) {
            loadmoreSongListTask.cancel(true);
        }
        loadmoreSongListTask = new LoadmoreSongListTask();
        loadmoreSongListTask.pushData(tinguid);
        loadmoreSongListTask.execute();
    }


    // TODO: 2018/2/16 刷新
    class RefreshSongListTask extends AsyncTask<Void, Void, SongListBean> {
        volatile String tinguid = "";

        public void pushData(String tinguid) {
            this.tinguid = tinguid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("请等待···", true);
        }

        @Override
        protected SongListBean doInBackground(Void... voids) {
            SongListBean songList = null;
            try {
                gson = new Gson();
                pageNo = 0;
                String str = HttpService.Instance(ctx).SongList(tinguid, pageNo, limitNo);
                songList = gson.fromJson(str, SongListBean.class);
            } catch (Exception e) {
                dkhandlder.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return songList;
        }

        @Override
        protected void onPostExecute(SongListBean result) {
            super.onPostExecute(result);
            msg = dkhandlder.obtainMessage();
            if (result != null) {
                msg.what = UPDATECODE;
                msg.obj = result;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandlder.sendMessage(msg);
        }
    }

    // TODO: 2018/2/16 加载
    class LoadmoreSongListTask extends AsyncTask<Void, Void, SongListBean> {
        volatile String tinguid = "";

        public void pushData(String tinguid) {
            this.tinguid = tinguid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("请等待···", true);

        }

        @Override
        protected SongListBean doInBackground(Void... voids) {
            SongListBean songList = null;
            try {
                gson = new Gson();
                pageNo++;
                String str = HttpService.Instance(ctx).SongList(tinguid, pageNo, limitNo);
                songList = gson.fromJson(str, SongListBean.class);
            } catch (Exception e) {
                dkhandlder.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return songList;
        }

        @Override
        protected void onPostExecute(SongListBean result) {
            super.onPostExecute(result);
            msg = dkhandlder.obtainMessage();
            if (result != null) {
                msg.what = UPDATECODE;
                msg.obj = result;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandlder.sendMessage(msg);
        }
    }

}
