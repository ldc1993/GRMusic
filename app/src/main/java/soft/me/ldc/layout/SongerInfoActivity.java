package soft.me.ldc.layout;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import soft.me.ldc.R;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.model.SongerInfoBean;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class SongerInfoActivity extends RootMusicActivity {

    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mIcon)
    AppCompatImageView mIcon;
    @BindView(R.id.mInfo)
    AppCompatTextView mInfo;
    @BindView(R.id.mMore)
    AppCompatTextView mMore;
    //
    GRLoadDialog loadDialog = null;
    //
    Message msg = null;
    //
    Gson gson = null;
    //网页url
    volatile String InfoUrl = "";
    //
    private RefreshInfoTask infoTask = null;
    //
    volatile PlayMusicSongBean mData = null;
    //
    final int SUCCESSCODE = 0x000;
    final int FAILEDCODE = 0x001;
    final int ERRORCODE = 0x002;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESSCODE:
                    SongerInfoBean bean = (SongerInfoBean) msg.obj;
                    if (bean != null) {
                        //图标
                        if (StringUtil.isNotBlank(bean.avatar_s1000)) {
                            Picasso.with(ctx).load(bean.avatar_s1000).resize(500, 500).centerInside().into(mIcon);
                        } else {
                            mIcon.setVisibility(View.GONE);
                        }
                        //信息
                        if (StringUtil.isNotBlank(bean.intro)) {
                            StringBuffer sb = new StringBuffer();
                            sb.append("姓名:" + bean.name + "\n");
                            sb.append("出生:" + bean.birth + "\n");
                            sb.append("地区:" + bean.country + "\n");
                            sb.append("公司:" + bean.company + "\n");
                            sb.append("MV数:" + bean.mv_total + "\n");
                            sb.append("歌曲数:" + bean.songs_total + "\n");
                            sb.append("星座:" + bean.constellation + "\n");
                            mInfo.setText(sb.toString() + bean.intro);
                        }
                        //页面信息
                        if (StringUtil.isNotBlank(bean.url)) {
                            InfoUrl = bean.url;
                        } else {
                            mMore.setVisibility(View.GONE);
                        }
                    } else {
                        dkhandler.sendEmptyMessage(FAILEDCODE);
                    }
                    break;
                case FAILEDCODE:
                    GRToastView.show(ctx, "获取失败~", Toast.LENGTH_SHORT);
                    break;
                case ERRORCODE:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        mData = (PlayMusicSongBean) getIntent().getSerializableExtra("data");
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_songer_info;
    }

    @Override
    protected void Main() {
        {
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setTitleText(mData.songinfo.author);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setSupportActionBar(mToolbar);
        }
        //初始化数据
        RunRefreshInfoTask(mData.songinfo.ting_uid);
    }


    // TODO: 2018/2/16 点击事件
    @OnClick(R.id.mMore)
    public void Click(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(InfoUrl);
        intent.setData(content_url);
        startActivity(intent);
    }

    //刷新
    private void RunRefreshInfoTask(String tinguid) {
        if (infoTask != null && !infoTask.isCancelled()) {
            infoTask.cancel(true);

        }
        infoTask = new RefreshInfoTask();
        infoTask.pushData(tinguid);
        infoTask.execute();

    }


    // TODO: 2018/2/16 刷新
    class RefreshInfoTask extends AsyncTask<Void, Void, SongerInfoBean> {
        String tinguid = "";

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
        protected SongerInfoBean doInBackground(Void... voids) {
            SongerInfoBean infoBean = null;
            try {
                gson = new Gson();
                String str = HttpService.Instance(ctx).SongerInfo(tinguid);
                infoBean = gson.fromJson(str, SongerInfoBean.class);
            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return infoBean;
        }

        @Override
        protected void onPostExecute(SongerInfoBean result) {
            super.onPostExecute(result);
            msg = dkhandler.obtainMessage();
            if (result != null) {
                msg.what = SUCCESSCODE;
                msg.obj = result;
            } else {
                msg.what = FAILEDCODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessage(msg);

        }
    }


}
