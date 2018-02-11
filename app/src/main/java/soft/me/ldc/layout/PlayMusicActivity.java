package soft.me.ldc.layout;

import android.content.Intent;
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
import soft.me.ldc.R;
import soft.me.ldc.adapter.PlayMusicAdapter;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.service.PlayService;
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
    PlayMusicCoverFragment playMusicCoverFragment = null;
    PlayMusicLyricFragment playMusicLyricFragment = null;
    //
    PlayMusicAdapter playMusicAdapter = null;
    List<Fragment> fragments = null;
    //是否播放新歌
    volatile boolean play_New_Song = false;

    static final int PlaySongCode = 0x000;//播放音乐
    static final int ERRORCODE = 0x003;//错误
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
                        } else {
                            dkhandler.sendEmptyMessage(ERRORCODE);
                        }
                    }
                    break;
                case ERRORCODE:
                    GRToastView.show(ctx, "无法播放", Toast.LENGTH_SHORT);
                    break;
            }
        }
    };


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
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
        //设置滑动
        mSeekbar.setOnSeekBarChangeListener(new OnSeekBarListener());


    }

    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }

    //获取服务
    protected PlayService getPlayService() {
        return playService;
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
            mToolbar.setRightText("" + progress + "%");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

}
