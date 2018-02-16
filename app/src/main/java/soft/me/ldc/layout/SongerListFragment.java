package soft.me.ldc.layout;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import soft.me.ldc.adapter.SongerListAdapter;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.model.SongerListBean;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.utils.NetUtil;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongerListFragment extends RootFragment {


    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    //
    GRLoadDialog loadDialog = null;
    //
    volatile int pageNo = 0;
    final int limitNo = 100;
    //
    SongerListAdapter songerListAdapter = null;
    StaggeredGridLayoutManager staggeredGridLayoutManager = null;
    //消息
    Message msg = null;
    //
    Bundle bundle = null;
    //
    Gson gson = null;
    //
    RefreshSongerTask refreshSongerTask = null;
    LoadmoreSongerTask loadmoreSongerTask = null;
    //
    final int REFRESHCODE = 0x001;
    final int LOADMORECODE = 0x002;
    final int UPDATECODE = 0x003;
    final int ERRORCODE = 0x004;
    final int NODATACODE = 0x005;
    Handler dkhandlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESHCODE:
                    RunRefreshSongerTask();
                    break;
                case LOADMORECODE:
                    RunLoadmoreSongerTask();
                    break;
                case UPDATECODE:
                    SongerListBean data = (SongerListBean) msg.obj;
                    if (data != null) {
                        if (songerListAdapter == null)
                            songerListAdapter = new SongerListAdapter();
                        songerListAdapter.pushData(data);
                        songerListAdapter.notifyDataSetChanged();
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
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        return inflater.inflate(R.layout.fragment_artist_list, null, false);
    }

    @Override
    protected void Init() throws Exception {
        smartrefreshlayout.setEnableAutoLoadmore(false);
        smartrefreshlayout.setOnRefreshListener(new OnRefreshLister());
        smartrefreshlayout.setOnLoadmoreListener(new OnRefreshLister());

    }

    @Override
    protected void Main() throws Exception {
        if (staggeredGridLayoutManager == null)
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        if (songerListAdapter == null)
            songerListAdapter = new SongerListAdapter();
        songerListAdapter.pushData(null);
        songerListAdapter.setListener(new OnItemListener());

        mList.setLayoutManager(staggeredGridLayoutManager);
        mList.setHasFixedSize(true);
        mList.setLayoutFrozen(true);
        mList.setAdapter(songerListAdapter);

        //初始化数据
        dkhandlder.sendEmptyMessage(REFRESHCODE);

    }

    // TODO: 2018/2/16 刷新
    class OnRefreshLister implements OnRefreshListener, OnLoadmoreListener {

        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {
            dkhandlder.sendEmptyMessage(LOADMORECODE);
            refreshlayout.finishLoadmore(2000);


        }

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            dkhandlder.sendEmptyMessage(REFRESHCODE);
            refreshlayout.finishRefresh(2000);
        }
    }

    // TODO: 2018/2/16 点击事件
    class OnItemListener implements SongerListAdapter.OnItemListener {

        @Override
        public void onItem(View v, SongerListBean.ArtistBean type) {
            if (NetUtil.isAvailable(ctx)) {
                bundle = new Bundle();
                bundle.putSerializable("songer", type);
                Intent it = new Intent(ctx, SongListActivity.class);
                it.putExtras(bundle);
                startActivity(it);
            } else {
                NetUtil.NetSetting(ctx);
            }
        }
    }


    private void RunRefreshSongerTask() {
        if (refreshSongerTask != null && !refreshSongerTask.isCancelled()) {
            refreshSongerTask.cancel(true);
        }
        refreshSongerTask = new RefreshSongerTask();
        refreshSongerTask.execute();
    }

    private void RunLoadmoreSongerTask() {
        if (loadmoreSongerTask != null && !loadmoreSongerTask.isCancelled()) {
            loadmoreSongerTask.cancel(true);
        }
        loadmoreSongerTask = new LoadmoreSongerTask();
        loadmoreSongerTask.execute();
    }


    // TODO: 2018/2/16 刷新
    class RefreshSongerTask extends AsyncTask<Void, Void, SongerListBean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("请等待···", true);
        }

        @Override
        protected SongerListBean doInBackground(Void... voids) {
            SongerListBean songer = null;
            try {
                gson = new Gson();
                pageNo = 0;
                String str = HttpService.Instance(ctx).SongerList(pageNo, limitNo);
                songer = gson.fromJson(str, SongerListBean.class);
            } catch (Exception e) {
                dkhandlder.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return songer;
        }

        @Override
        protected void onPostExecute(SongerListBean result) {
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
    class LoadmoreSongerTask extends AsyncTask<Void, Void, SongerListBean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("请等待···", true);

        }

        @Override
        protected SongerListBean doInBackground(Void... voids) {
            SongerListBean songer = null;
            try {
                gson = new Gson();
                pageNo++;
                String str = HttpService.Instance(ctx).SongerList(pageNo, limitNo);
                songer = gson.fromJson(str, SongerListBean.class);
            } catch (Exception e) {
                dkhandlder.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return songer;
        }

        @Override
        protected void onPostExecute(SongerListBean result) {
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
