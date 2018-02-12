package soft.me.ldc.layout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.R;
import soft.me.ldc.adapter.PlayMusicAdapter;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.service.PlayService;
import soft.me.ldc.utils.ToFormat;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;
import soft.me.ldc.view.GRViewPager;

public class PlayMusicActivity extends RootActivity {

    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mReset)
    AppCompatImageView mReset;
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
    //数据
    volatile PlayMusicSongBean mData = null;
    //消息
    Message msg = null;
    //
    ScheduledExecutorService scheduledThreadPoolExecutor = null;
    //
    PlayMusicCoverFragment playMusicCoverFragment = null;
    PlayMusicLyricFragment playMusicLyricFragment = null;
    //
    PlayMusicAdapter playMusicAdapter = null;
    List<Fragment> fragments = null;
    //是否播放新歌
    volatile boolean play_New_Song = false;
    //当前音乐进度
    volatile int lastCurrSize = 0;
    volatile int laseAllSize = 0;
    //
    static final int PlaySongCode = 0x000;//播放音乐
    static final int ShowPlayInfo = 0x001;//显示信息
    static final int UpdatePlayProgressCode = 0x002;//更新进度
    static final int ERRORCODE = 0x004;//错误
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PlaySongCode:
                    if (playService.Player() != null) {
                        if (mData != null) {
                            //播放新歌
                            if (play_New_Song) {
                                playService.PushData(mData);
                                playService.Play();
                            }
                            //播放状态
                            if (playService.Player().isPlaying()) {
                                mPlayorPause.setImageResource(R.drawable.play_state_pause);
                            } else {
                                mPlayorPause.setImageResource(R.drawable.play_state_play);
                            }
                            //显示数据
                            dkhandler.sendEmptyMessage(ShowPlayInfo);
                        } else {
                            dkhandler.sendEmptyMessage(ERRORCODE);
                        }
                    }
                    break;
                case ERRORCODE:
                    GRToastView.show(ctx, "无法播放", Toast.LENGTH_SHORT);
                    break;
                case ShowPlayInfo:
                    laseAllSize = playService.getDuration();
                    mSongCurr.setText(lastCurrSize + "");
                    mSongSize.setText(ToFormat.formatTime(laseAllSize) + "");
                    mSeekbar.setMax(laseAllSize);
                    break;
                case UpdatePlayProgressCode:
                    int progress = (Integer) msg.obj;
                    boolean ok = IsPlayFnish(progress);
                    mSongCurr.setText(ToFormat.formatTime(lastCurrSize) + "");
                    mSeekbar.setProgress(lastCurrSize);
                    if (ok) {
                        playService.Reset();
                        mPlayorPause.setImageResource(R.drawable.play_state_play);
                        lastCurrSize = 0;
                    }
                    break;
            }
        }
    };


    //是否播放完成
    private boolean IsPlayFnish(int number) {
        boolean success = false;
        if (number > 0 && lastCurrSize == number) {
            success = true;
        }
        lastCurrSize = number;
        return success;
    }


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        ActivityList.addActivity(this);
        //接受数据
        mData = (PlayMusicSongBean) getIntent().getSerializableExtra("play");
        play_New_Song = getIntent().getExtras().getBoolean("play_new_song");
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void Main() {
        if (scheduledThreadPoolExecutor == null)
            scheduledThreadPoolExecutor = Executors.newSingleThreadScheduledExecutor();
        {
            mToolbar.setTitleText("" + mData.songinfo.title);
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        {
            if (playMusicCoverFragment == null)
                playMusicCoverFragment = new PlayMusicCoverFragment();
            playMusicCoverFragment.pushData(mData);
            if (playMusicLyricFragment == null)
                playMusicLyricFragment = new PlayMusicLyricFragment();
            if (playMusicAdapter == null)
                playMusicAdapter = new PlayMusicAdapter(fragmentManager);
            if (fragments == null)
                fragments = new ArrayList<>();
            fragments.add(playMusicCoverFragment);
            fragments.add(playMusicLyricFragment);
            //添加数据
            playMusicAdapter.pushData(fragments);
            //
            mViewPager.setAdapter(playMusicAdapter);
            mViewPager.setCurrentItem(0);
            mViewPager.setScrollEnable(true);
            mViewPager.setOffscreenPageLimit(2);

        }
        //播放歌曲
        dkhandler.sendEmptyMessage(PlaySongCode);
        //
        RunThread();
        //滑动事件
        mSeekbar.setOnSeekBarChangeListener(new OnSeekBarListener());


    }

    @Override
    protected void Error(Exception e) {
        Log.e("LLL", "" + e.getLocalizedMessage());
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }

    //获取服务
    protected PlayService getPlayService() {
        return playService;
    }

    //执行线程
    private void RunThread() {
        if (scheduledThreadPoolExecutor != null)
            scheduledThreadPoolExecutor.scheduleAtFixedRate(new SingleThread(), 1, 1, TimeUnit.SECONDS);
    }

    //单线程
    class SingleThread extends Thread {

        @Override
        public void run() {
            msg = dkhandler.obtainMessage(UpdatePlayProgressCode);
            msg.obj = playService.getCurrentPosition();
            dkhandler.sendMessage(msg);
        }
    }

    // TODO: 2018/1/16 点击事件
    @OnClick({R.id.mReset, R.id.mPlayorPause, R.id.mNext})
    public void ClickListener(View view) {
        switch (view.getId()) {
            case R.id.mReset://重置
                playService.Reset();
                mPlayorPause.setImageResource(R.drawable.play_state_play);
                break;
            case R.id.mPlayorPause:
                if (playService.Player() != null && mData != null) {
                    if (playService.Player().isPlaying()) {
                        playService.Pause();
                        mPlayorPause.setImageResource(R.drawable.play_state_play);
                    } else {
                        playService.Play();
                        mPlayorPause.setImageResource(R.drawable.play_state_pause);
                    }
                }
                break;
            case R.id.mNext:
                break;

        }
    }


    // TODO: 2018/1/19 滑动事件
    class OnSeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (playService.Player() != null && playService.Player().isPlaying() && fromUser)
                playService.Player().seekTo(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
    // TODO: 2018/2/11 生命周期

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scheduledThreadPoolExecutor != null && !scheduledThreadPoolExecutor.isTerminated()) {
            scheduledThreadPoolExecutor.shutdown();
        }
    }
}
