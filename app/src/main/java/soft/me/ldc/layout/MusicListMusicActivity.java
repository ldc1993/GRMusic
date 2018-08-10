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
import soft.me.ldc.adapter.MusicListAdapter;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.common.pool.MultiThreadPool;
import soft.me.ldc.model.MusicListBean;
import soft.me.ldc.model.MusicTypeBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.task.PlayMusicTask;
import soft.me.ldc.utils.NetUtil;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToolbar;
import soft.me.ldc.view.ToastView;

public class MusicListMusicActivity extends RootMusicActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    @BindView(R.id.mList)
    RecyclerView mList;

    MusicTypeBean musicTypeBean = null;
    //分页
    int pageNo = 0;
    //消息
    Message msg = null;
    //持久任务
    RefreshTask refreshTask = null;
    LoadmoreTask loadmoreTask = null;
    //数据
    MusicListBean mData = null;
    Gson gson = null;
    //
    LinearLayoutManager llm = null;
    MusicListAdapter musicListAdapter = null;
    //等待对话框
    private GRLoadDialog loadDialog = null;

    final int REFRESHCODE = 0x000;//下拉刷新
    final int LOADMORECODE = 0x001;//上拉刷新
    final int UPDATEDATACODE = 0x002;//更新数据
    final int NODATACODE = 0x003;//错误
    final int ERRORCODE = 0x004;//错误
    private Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESHCODE:
                    RefreshTaskRun();
                    break;
                case LOADMORECODE:
                    LoadmoreTaskRun();
                    break;
                case UPDATEDATACODE:
                    mData = (MusicListBean) msg.obj;
                    if (mData != null && mData.song_list.size() > 0) {
                        if (musicListAdapter != null) {
                            musicListAdapter.pushData(mData.song_list);
                            musicListAdapter.notifyDataSetChanged();
                        }
                        if (mList != null)
                            mList.smoothScrollToPosition(0);
                    } else {
                        dkhandler.sendEmptyMessage(ERRORCODE);
                    }

                    break;
                case NODATACODE:
                    ToastView.show(ctx, "没有数据!", Toast.LENGTH_SHORT);
                    break;
                case ERRORCODE:
                    ToastView.show(ctx, "加载错误!", Toast.LENGTH_SHORT);
                    break;

            }
        }
    };


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        ActivityList.addActivity(this);
        musicTypeBean = (MusicTypeBean) getIntent().getSerializableExtra("type");

    }

    @Override
    protected Integer UI() {
        return R.layout.activity_music;
    }


    @Override
    protected void Main() {
        {
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setTitleText("" + musicTypeBean.typeName);
            mToolbar.setColorRes(R.color.colorPrimary);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setSupportActionBar(mToolbar);
        }

        if (gson == null)
            gson = new Gson();
        //刷新监听
        smartrefreshlayout.setOnRefreshListener(new RefreshListener());
        smartrefreshlayout.setOnLoadmoreListener(new RefreshListener());
        smartrefreshlayout.setEnableAutoLoadmore(false);

        if (llm == null)
            llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        if (mData == null)
            mData = new MusicListBean();
        if (musicListAdapter == null)
            musicListAdapter = new MusicListAdapter();
        musicListAdapter.pushData(mData.song_list);
        musicListAdapter.setListener(new ItemListener());
        mList.setLayoutFrozen(true);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(llm);
        mList.setAdapter(musicListAdapter);
        //加载任务
        dkhandler.sendEmptyMessage(REFRESHCODE);


    }

    // TODO: 2018/1/13  刷新事件
    class RefreshListener implements OnRefreshListener, OnLoadmoreListener {


        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {
            msg = new Message();
            msg.what = LOADMORECODE;
            dkhandler.sendMessage(msg);
            refreshlayout.finishLoadmore(2000);

        }

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            msg = new Message();
            msg.what = REFRESHCODE;
            dkhandler.sendMessage(msg);
            refreshlayout.finishRefresh(2000);
        }
    }

    //item事件
    class ItemListener implements MusicListAdapter.OnItemListener {
        @Override
        public void onItem(View view, MusicListBean.SongListBean type) {
            if (NetUtil.isAvailable(ctx)) {
                try {
                    PlayMusicTask playMusicTask = PlayMusicTask.Instance(ctx, 1);
                    playMusicTask.pushData(type.song_id);
                    playMusicTask.pushPlayState(true);
                    MultiThreadPool.newInsance().pushThread(playMusicTask);
                } catch (Exception e) {
                    ToastView.show(ctx, "错误!", Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }
            } else {
                NetUtil.NetSetting(ctx);
            }
        }
    }

    // TODO: 2018/1/13 执行下拉刷新任务
    private void RefreshTaskRun() {
        if (refreshTask != null && !refreshTask.isCancelled()) {
            refreshTask.cancel(true);
        }
        refreshTask = new RefreshTask();
        refreshTask.execute();
    }

    // TODO: 2018/1/13 执行上拉刷新任务
    private void LoadmoreTaskRun() {
        if (loadmoreTask != null && !loadmoreTask.isCancelled()) {
            loadmoreTask.cancel(true);
        }
        loadmoreTask = new LoadmoreTask();
        loadmoreTask.execute();
    }


    //下拉刷新
    class RefreshTask extends AsyncTask<Void, Void, MusicListBean> {
        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("数据加载中···", true);
        }

        @Override
        protected MusicListBean doInBackground(Void... voids) {
            MusicListBean result = null;
            try {
                pageNo = 0;
                String str = HttpService.INSTANCE.Service(ctx).MusicList(musicTypeBean.typeCode, 20 + "", pageNo + "");
                if (gson == null)
                    gson = new Gson();
                result = gson.fromJson(str, MusicListBean.class);
            } catch (Exception e) {

                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(MusicListBean result) {
            msg = new Message();
            if (result != null) {
                msg.what = UPDATEDATACODE;
                msg.obj = result;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);
        }
    }

    //上拉刷新
    class LoadmoreTask extends AsyncTask<Void, Void, MusicListBean> {
        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("数据加载中···", true);
        }

        @Override
        protected MusicListBean doInBackground(Void... voids) {
            MusicListBean result = null;
            try {
                pageNo++;
                String str = HttpService.INSTANCE.Service(ctx).MusicList(musicTypeBean.typeCode, 20 + "", pageNo + "");
                if (gson == null)
                    gson = new Gson();
                result = gson.fromJson(str, MusicListBean.class);
            } catch (Exception e) {

                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(MusicListBean result) {
            msg = new Message();
            if (result != null) {
                msg.what = UPDATEDATACODE;
                msg.obj = result;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);
        }
    }

}
