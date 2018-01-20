package soft.me.ldc.layout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.R;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.model.RadioStationSongBean;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.service.PlayService;
import soft.me.ldc.view.GRLoadDialog;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;
import soft.me.ldc.view.GRViewPager;

public class PlayMusicActivity extends RootActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mPrev)
    AppCompatImageView mPrev;
    @BindView(R.id.mPlayorPause)
    AppCompatImageView mPlayorPause;
    @BindView(R.id.mNext)
    AppCompatImageView mNext;
    @BindView(R.id.mSeekbar)
    SeekBar mSeekbar;
    @BindView(R.id.mViewPager)
    GRViewPager mViewPager;
    @BindView(R.id.mSongCurr)
    AppCompatTextView mSongCurr;
    @BindView(R.id.mSongSize)
    AppCompatTextView mSongSize;
    //要接收的数据
    volatile RadioStationSongBean.ResultBean.SonglistBean songlistBean = null;
    //数据
    volatile PlayMusicSongBean mData = null;
    //
    Gson gson = null;
    //
    GRLoadDialog loadDialog = null;
    //消息
    Message msg = null;
    //持久任务
    RefreshTask refreshTask = null;

    static final int REFRESHDATACODE = 0x000;//刷新数据
    static final int UPDATEDATACODE = 0x001;//更新数据
    static final int NOTDATACODE = 0x002;//没有数据
    static final int ERRORCODE = 0x003;//错误
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESHDATACODE:
                    if (songlistBean != null) {
                        RunRefreshTask(songlistBean.songid);
                    } else {
                        dkhandler.sendEmptyMessage(ERRORCODE);
                    }
                    break;
                case UPDATEDATACODE:
                    mData = (PlayMusicSongBean) msg.obj;
                    if (mData != null) {

                    } else {
                        dkhandler.sendEmptyMessage(NOTDATACODE);
                    }
                    break;
                case NOTDATACODE:
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
        //接受数据
        songlistBean = (RadioStationSongBean.ResultBean.SonglistBean) getIntent().getSerializableExtra("type");
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void Main() {
        {
            mToolbar.setTitleText("" + songlistBean.title);
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        //加载数据
        dkhandler.sendEmptyMessage(REFRESHDATACODE);
        if (gson == null)
            gson = new Gson();
        //设置滑动
        mSeekbar.setOnSeekBarChangeListener(new OnSeekBarListener());


    }

    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }

    // TODO: 2018/1/16 点击事件
    @OnClick({R.id.mPrev, R.id.mPlayorPause, R.id.mNext})
    public void ClickListener(View view) {
        switch (view.getId()) {
            case R.id.mPrev:
                playService.Pause();
                break;
            case R.id.mPlayorPause:
                playService.Data(mData);
                playService.Play();
                break;
            case R.id.mNext:
                GRToastView.show(ctx, "下一首", Toast.LENGTH_SHORT);
                break;

        }
    }


    // TODO: 2018/1/19 滑动事件
    class OnSeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mToolbar.setRightText("" + progress + "%");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            mToolbar.setRightText("开始拖动");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mToolbar.setRightText("停止拖动");
        }
    }


    //执行任务
    private void RunRefreshTask(String qry) {
        if (refreshTask != null && !refreshTask.isCancelled()) {
            refreshTask.cancel(true);
        }
        refreshTask = new RefreshTask();
        refreshTask.pushData(qry);
        refreshTask.execute();

    }

    //加载数据
    class RefreshTask extends AsyncTask<Void, Void, PlayMusicSongBean> {
        String qry = "";

        public void pushData(String qry) {
            this.qry = qry;
        }

        @Override
        protected void onPreExecute() {
            if (loadDialog != null && loadDialog.isShow())
                loadDialog.dismiss();
            loadDialog = GRLoadDialog.Instance(ctx, GRLoadDialog.Style.White).show("数据加载中···", true);
        }

        @Override
        protected PlayMusicSongBean doInBackground(Void... voids) {
            PlayMusicSongBean bean = null;
            try {
                if (gson == null)
                    gson = new Gson();
                String str = HttpService.Instance(ctx).PlayMusicSong(songlistBean.songid);
                bean = gson.fromJson(str, PlayMusicSongBean.class);
            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ERRORCODE);
                e.printStackTrace();
            }
            return bean;
        }

        @Override
        protected void onPostExecute(PlayMusicSongBean result) {
            msg = new Message();
            if (result != null) {
                msg.what = UPDATEDATACODE;
                msg.obj = result;
            } else {
                msg.what = NOTDATACODE;
            }
            loadDialog.dismiss();
            dkhandler.sendMessageDelayed(msg, 1000);
        }
    }

}
