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

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.QueryMusicAdapter;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.common.pool.MultiThreadPool;
import soft.me.ldc.model.QueryMusicBean;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.task.PlayMusicTask;
import soft.me.ldc.utils.NetUtil;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRSearchView;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryMusicFragment extends RootFragment {

    @BindView(R.id.mSearcView)
    GRSearchView mSearcView;
    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    Gson gson = null;
    //
    GRLoadDialog loadDialog = null;
    //持久任务
    RefreshQryTask refreshQryTask = null;
    LoadmoreQryTask loadmoreQryTask = null;
    //
    LinearLayoutManager llm = null;
    QueryMusicAdapter queryMusicAdapter = null;

    //
    int PageNO = 0;
    //
    Message msg = null;
    //
     final int REFRESHCODE = 0x000;//下拉刷新
     final int LOADMORECODE = 0x001;//上拉刷新
     final int UPDATEVIEWCODE = 0x002;//更新数据
     final int NODATACODE = 0x003;//没有数据
     final int ERRORCODE = 0x004;//错误


    private Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESHCODE:
                    RunRefreshTask("光良");
                    break;
                case LOADMORECODE:
                    RunRefreshTask(mSearcView.getKey().toString());
                    break;
                case UPDATEVIEWCODE:
                    QueryMusicBean data = (QueryMusicBean) msg.obj;
                    if (data != null) {
                        if (data.result != null && data.result.song_info.song_list != null && data.result.song_info.song_list.size() > 0) {

                            if (queryMusicAdapter != null) {
                                queryMusicAdapter.pushData(data.result.song_info.song_list);
                                queryMusicAdapter.notifyDataSetChanged();
                            }
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
                    GRToastView.show(ctx, "加载失败!", Toast.LENGTH_SHORT);
                    break;
            }
        }
    };


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        View mainView = inflater.inflate(R.layout.fragment_query_music, null, false);
        mainView.setFocusable(true);
        return mainView;

    }

    @Override
    protected void Init() throws Exception {
        mSearcView.setHint("请输入关键词");
        mSearcView.setKey(null);
        mSearcView.setSearchBtnListener(new QueryListener());
        {
            smartrefreshlayout.setOnRefreshListener(new RefreshListener());
            smartrefreshlayout.setOnLoadmoreListener(new RefreshListener());
            smartrefreshlayout.setEnableAutoLoadmore(false);
        }

    }

    @Override
    protected void Main() throws Exception {
        if (llm == null)
            llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, true);
        if (queryMusicAdapter == null)
            queryMusicAdapter = new QueryMusicAdapter();
        queryMusicAdapter.pushData(null);
        queryMusicAdapter.setListener(new ItemListener());
        //设置属性
        mList.setLayoutManager(llm);
        mList.setHasFixedSize(true);
        mList.setLayoutFrozen(true);
        mList.setAdapter(queryMusicAdapter);
        //加载任务
        dkhandler.sendEmptyMessage(REFRESHCODE);
    }

    //下拉刷新
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

    //查询列表item点击事件
    class ItemListener implements QueryMusicAdapter.OnItemListener {

        @Override
        public void ItemClick(View view, QueryMusicBean.ResultBean.SongInfoBean.SongListBean type) {
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


    //查询事件
    class QueryListener implements GRSearchView.onSearchListener {

        @Override
        public void onSearchClick(View view, String key) {
            if (StringUtil.isNotBlank(key)) {
                RunLoadmoreTask(key);
            } else {
                RunRefreshTask("");
            }
        }
    }

    //执行加载任务
    private void RunRefreshTask(String qry) {
        if (refreshQryTask != null && !refreshQryTask.isCancelled()) {
            refreshQryTask.cancel(true);
        }
        refreshQryTask = new RefreshQryTask();
        refreshQryTask.pushQry(qry);
        refreshQryTask.execute();

    }

    //执行加载任务
    private void RunLoadmoreTask(String qry) {
        if (loadmoreQryTask != null && !loadmoreQryTask.isCancelled()) {
            loadmoreQryTask.cancel(true);
        }
        loadmoreQryTask = new LoadmoreQryTask();
        loadmoreQryTask.pushQry(qry);
        loadmoreQryTask.execute();

    }


    //刷新数据
    class RefreshQryTask extends AsyncTask<Void, Void, QueryMusicBean> {

        String qry = "";

        public void pushQry(String qry) {
            this.qry = qry;
        }

        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("", true);
        }

        @Override
        protected QueryMusicBean doInBackground(Void... voids) {
            QueryMusicBean queryMusicBean = null;

            try {
                if (gson == null)
                    gson = new Gson();
                PageNO = 0;
                String str = HttpService.Instance(ctx).QueryMusic(qry, "" + PageNO, "" + 20);
                queryMusicBean = gson.fromJson(str, QueryMusicBean.class);

            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return queryMusicBean;
        }

        @Override
        protected void onPostExecute(QueryMusicBean queryMusicBean) {
            msg = new Message();
            if (queryMusicBean != null) {
                msg.what = UPDATEVIEWCODE;
                msg.obj = queryMusicBean;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);

        }
    }


    //刷新数据
    class LoadmoreQryTask extends AsyncTask<Void, Void, QueryMusicBean> {

        String qry = "";

        public void pushQry(String qry) {
            this.qry = qry;
        }

        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("", true);
        }

        @Override
        protected QueryMusicBean doInBackground(Void... voids) {
            QueryMusicBean queryMusicBean = null;

            try {
                if (gson == null)
                    gson = new Gson();
                PageNO++;
                String str = HttpService.Instance(ctx).QueryMusic(qry, "" + PageNO, "" + 20);
                queryMusicBean = gson.fromJson(str, QueryMusicBean.class);

            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return queryMusicBean;
        }

        @Override
        protected void onPostExecute(QueryMusicBean queryMusicBean) {
            msg = new Message();
            if (queryMusicBean != null) {
                msg.what = UPDATEVIEWCODE;
                msg.obj = queryMusicBean;
            } else {
                msg.what = NODATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);

        }
    }
}
