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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.MusicListAdapter;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.model.Music;
import soft.me.ldc.model.MusicType;
import soft.me.ldc.service.MusicService;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class MusicListActivity extends RootActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mList)
    RecyclerView mList;

    MusicType musicType = null;
    //分页
    int pageNo = 0;
    //消息
    Message msg = null;
    //持久任务
    RefreshTask refreshTask = null;
    LoadmoreTask loadmoreTask = null;
    //数据
    Music music = null;
    Gson gson = null;
    //
    LinearLayoutManager llm = null;
    MusicListAdapter musicListAdapter = null;

    static final int REFRESHCODE = 0x000;//下拉刷新
    static final int LOADMORECODE = 0x001;//上拉刷新
    static final int UPDATEDATACODE = 0x002;//更新数据
    static final int ERRORCODE = 0x003;//错误
    Handler dkhandler = new Handler() {
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
                    music = (Music) msg.obj;
                    if (musicListAdapter != null)
                        musicListAdapter.pushData(music.song_list);
                    musicListAdapter.notifyDataSetChanged();
                    break;
                case ERRORCODE:
                    GRToastView.show(ctx, "加载失败!", Toast.LENGTH_SHORT);
                    break;

            }
        }
    };


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        musicType = (MusicType) getIntent().getSerializableExtra("type");
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_music;
    }


    @Override
    protected void Main() {
        {
            mToolbar.setLeftText("返回");
            mToolbar.setTitleText("" + musicType.typeName);
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

        //加载任务
        RefreshTaskRun();

        if (llm == null)
            llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);

        if (musicListAdapter == null)
            musicListAdapter = new MusicListAdapter();
        musicListAdapter.setListener(new ItemListener());


        mList.setLayoutFrozen(true);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(llm);
        mList.setAdapter(musicListAdapter);


    }


    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
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

    class ItemListener implements MusicListAdapter.OnItemListener {


        @Override
        public void onItem(View view, Music type) {
            GRToastView.show(ctx, type.billboard.comment, Toast.LENGTH_SHORT);
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
    class RefreshTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (music != null)
                    music = null;
                pageNo = 0;
                music = MusicService.MusicList(musicType.typeCode, 20, pageNo);
            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            msg = new Message();
            msg.what = UPDATEDATACODE;
            msg.obj = music;
            dkhandler.sendMessageDelayed(msg, 1500);
        }
    }

    //上拉刷新
    class LoadmoreTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (music != null)
                    music = null;
                pageNo++;
                music = MusicService.MusicList(musicType.typeCode, 20, pageNo);
            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            msg = new Message();
            msg.what = UPDATEDATACODE;
            msg.obj = music;
            dkhandler.sendMessageDelayed(msg, 1500);
        }
    }


}
