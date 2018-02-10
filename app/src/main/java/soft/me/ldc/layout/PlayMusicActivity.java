package soft.me.ldc.layout;

import android.os.AsyncTask;
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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.R;
import soft.me.ldc.adapter.PlayMusicAdapter;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.model.RadioStationSongBean;
import soft.me.ldc.service.HttpService;
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
    //
    volatile boolean isPlay = false;

    static final int REFRESHDATACODE = 0x000;//刷新数据
    static final int UPDATEDATACODE = 0x001;//更新数据
    static final int NOTDATACODE = 0x002;//没有数据
    static final int ERRORCODE = 0x003;//错误
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESHDATACODE:
                    if (mData != null && mData.songinfo != null) {

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
        mData = (PlayMusicSongBean) getIntent().getSerializableExtra("play");
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
        //加载数据
        dkhandler.sendEmptyMessage(REFRESHDATACODE);
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
                GRToastView.show(ctx, "暂停", Toast.LENGTH_SHORT);
                playService.Pause();
                break;
            case R.id.mPlayorPause:
                GRToastView.show(ctx, "播放", Toast.LENGTH_SHORT);
                playService.PushData(mData);
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

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }


}
