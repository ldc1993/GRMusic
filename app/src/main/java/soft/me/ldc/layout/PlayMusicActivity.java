package soft.me.ldc.layout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import soft.me.ldc.R;
import soft.me.ldc.adapter.PlayMusicAdapter;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.common.pool.MultiThreadPool;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.service.PlayService;
import soft.me.ldc.task.DownloadMusicTask;
import soft.me.ldc.utils.ToFormat;
import soft.me.ldc.view.GRToolbar;
import soft.me.ldc.view.GRViewPager;
import soft.me.ldc.view.ToastView;

public class PlayMusicActivity extends RootMusicActivity {

    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mReset)
    AppCompatImageView mReset;
    @BindView(R.id.mPlayorPause)
    AppCompatImageView mPlayorPause;
    @BindView(R.id.mDownload)
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
     final int PlaySongCode = 0x000;//播放音乐
     final int ShowPlayInfo = 0x001;//显示信息
     final int UpdatePlayProgressCode = 0x002;//更新进度
     final int ERRORCODE = 0x004;//错误
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PlaySongCode:
                    if (playService.HasEnable() && mData != null) {
                        //播放新歌
                        if (play_New_Song) {
                            playService.Play(mData);
                        }
                        //初始化显示
                        mSongCurr.setText("0:00");
                        mSongSize.setText("0:00");
                        //音乐进度
                        dkhandler.post(playRun);
                    } else {
                        dkhandler.sendEmptyMessage(ERRORCODE);
                    }

                    break;
                case ERRORCODE:
                    ToastView.show(ctx, "播放失败", Toast.LENGTH_SHORT);
                    break;
                case ShowPlayInfo:
                    //播放状态
                    if (playService.HasEnable()) {
                        if (laseAllSize != playService.getDuration()) {
                            laseAllSize = playService.getDuration();
                            mSongSize.setText(ToFormat.formatTime(laseAllSize) + "");
                            mSeekbar.setMax(laseAllSize);
                        }
                    }

                    break;
                case UpdatePlayProgressCode:
                    //显示数据
                    dkhandler.sendEmptyMessage(ShowPlayInfo);
                    //更新数据
                    int progress = (Integer) msg.obj;
                    boolean ok = IsPlayFnish(progress);
                    mSongCurr.setText(ToFormat.formatTime(lastCurrSize) + "");
                    mSeekbar.setProgress(lastCurrSize);
                    if (ok) {//重置
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
            playMusicLyricFragment.pushData(mData);
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
        //滑动事件
        mSeekbar.setOnSeekBarChangeListener(new OnSeekBarListener());


    }

    //获取服务
    protected PlayService.ServiceBind getPlayService() {
        return playService;

    }

    //播放进度
    private Runnable playRun = new Runnable() {
        @Override
        public void run() {
            if (playService.HasEnable() && playService.IsPlaying()) {
                //播放
                mPlayorPause.setImageResource(R.drawable.play_state_pause);
                msg = dkhandler.obtainMessage(UpdatePlayProgressCode);
                msg.obj = playService.getCurrentPosition();
                dkhandler.sendMessage(msg);

            } else {
                //未播放
                mPlayorPause.setImageResource(R.drawable.play_state_play);
            }
            //间隔速率至少1s 否则异常
            dkhandler.postDelayed(this, 1000);
        }
    };

    // TODO: 2018/1/16 点击事件
    @Optional
    @OnClick({R.id.mReset, R.id.mPlayorPause, R.id.mDownload})
    public void ClickListener(View view) {
        switch (view.getId()) {
            case R.id.mReset://重置
                playService.Reset();
                mPlayorPause.setImageResource(R.drawable.play_state_play);
                break;
            case R.id.mPlayorPause:
                if (playService.HasEnable() && playService.HasData()) {
                    if (playService.IsPlaying()) {
                        playService.Pause();
                        mPlayorPause.setImageResource(R.drawable.play_state_play);
                    } else {
                        playService.Start();
                        mPlayorPause.setImageResource(R.drawable.play_state_pause);
                    }
                }
                break;
            case R.id.mDownload:
                if (mData == null)
                    return;
                DownloadMusicTask downloadMusicTask = DownloadMusicTask.Instance(ctx, 1);
                downloadMusicTask.pushData(mData);
                MultiThreadPool.newInsance().pushThread(downloadMusicTask);
                break;

        }
    }


    // TODO: 2018/1/19 滑动事件
    class OnSeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (playService.HasEnable() && playService.IsPlaying() && fromUser)
                playService.SeekTo(progress);
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
        dkhandler.removeCallbacks(playRun);
    }
}
