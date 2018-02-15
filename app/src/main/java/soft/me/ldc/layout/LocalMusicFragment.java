package soft.me.ldc.layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.LocalMusicListAdapter;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.common.pool.MultiThreadPool;
import soft.me.ldc.model.LocalMusicBean;
import soft.me.ldc.task.PlayLocalMusicTask;
import soft.me.ldc.utils.MusicManager;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalMusicFragment extends RootFragment {


    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    //
    LinearLayoutManager llm = null;
    //
    LocalMusicListAdapter localMusicListAdapter = null;
    //
    QueryMusicTask queryMusicTask = null;
    //
    GRLoadDialog loadDialog = null;
    //消息
    Message msg = null;
    final static int ERRORCODE = 0x000;
    final static int REFRESHCODE = 0x001;
    final static int NODATACODE = 0x002;
    final static int UPDATEDATACODE = 0x003;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ERRORCODE:
                    GRToastView.show(ctx, "错误~", Toast.LENGTH_SHORT);
                    break;
                case REFRESHCODE:
                    RunQueryMusicTask();
                    break;
                case NODATACODE:
                    GRToastView.show(ctx, "没有数据~", Toast.LENGTH_SHORT);
                    break;
                case UPDATEDATACODE:
                    List<LocalMusicBean> beans = (List<LocalMusicBean>) msg.obj;
                    if (localMusicListAdapter == null)
                        localMusicListAdapter = new LocalMusicListAdapter();
                    localMusicListAdapter.pushData(beans);
                    localMusicListAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        return inflater.inflate(R.layout.fragment_local_music, null, false);
    }

    @Override
    protected void Init() throws Exception {
        smartrefreshlayout.setOnRefreshListener(new RefreshListener());
        {
            if (llm == null)
                llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
            if (localMusicListAdapter == null)
                localMusicListAdapter = new LocalMusicListAdapter();
            localMusicListAdapter.pushData(null);
            localMusicListAdapter.setItemListener(new OnItemListener());
            //
            mList.setLayoutManager(llm);
            mList.setHasFixedSize(true);
            mList.setLayoutFrozen(true);
            mList.setAdapter(localMusicListAdapter);
        }
        //初始化数据
        dkhandler.sendEmptyMessage(REFRESHCODE);
    }

    @Override
    protected void Main() throws Exception {

    }

    //执行任务
    private void RunQueryMusicTask() {
        if (queryMusicTask != null && !queryMusicTask.isCancelled()) {
            queryMusicTask.cancel(true);
        }
        queryMusicTask = new QueryMusicTask();
        queryMusicTask.execute();
    }

    //ListItem点击事件
    class OnItemListener implements LocalMusicListAdapter.OnItemListener {

        @Override
        public void onItem(View view, LocalMusicBean type) {
            PlayLocalMusicTask playLocalMusicTask = PlayLocalMusicTask.Instance(ctx, 1);
            playLocalMusicTask.pushData(type);
            playLocalMusicTask.pushPlayState(true);
            MultiThreadPool.newInsance().pushThread(playLocalMusicTask);
        }
    }

    //刷新事件
    class RefreshListener implements OnRefreshListener {

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            RunQueryMusicTask();
            refreshlayout.finishRefresh(2000);
        }
    }

    //获取本地歌曲任务
    class QueryMusicTask extends AsyncTask<Void, Void, List<LocalMusicBean>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("", true);
        }

        @Override
        protected List<LocalMusicBean> doInBackground(Void... voids) {
            List<LocalMusicBean> bean = null;
            try {
                bean = MusicManager.Instance(ctx).QueryMusic();
            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
            }
            return bean;
        }

        @Override
        protected void onPostExecute(List<LocalMusicBean> resut) {
            super.onPostExecute(resut);
            msg = dkhandler.obtainMessage();
            if (resut != null && resut.size() > 0) {
                msg.what = UPDATEDATACODE;
                msg.obj = resut;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);
        }
    }
}
