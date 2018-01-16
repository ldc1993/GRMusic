package soft.me.ldc.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.R;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.service.PlayService;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class PlayMusicActivity extends RootActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mPlay)
    AppCompatButton mPlay;
    @BindView(R.id.mPause)
    AppCompatButton mPause;
    @BindView(R.id.mStop)
    AppCompatButton mStop;
    //
    Intent playServiceIt = null;
    static String uriStr = "http://zhangmenshiting.qianqian.com/data2/music/135401651/135401651.mp3?xcode=f735c7d87a32c0a00c0d8753917afb85";
    Bundle bundle = null;
    //
    volatile String titleStr = "音乐播放器";


    @Override
    protected void onDestroy() {
        if (playServiceIt != null)
            stopService(playServiceIt);
        super.onDestroy();
    }

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        titleStr = getIntent().getStringExtra("title");
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void Main() {
        {
            mToolbar.setTitleText("" + titleStr);
            mToolbar.setLeftImg(R.mipmap.back_icon);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (bundle == null)
            bundle = new Bundle();

        {
            if (playServiceIt == null)
                playServiceIt = new Intent();
            playServiceIt.setClass(ctx, PlayService.class);
        }

        //
        mStop.setEnabled(false);
    }

    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }

    // TODO: 2018/1/16 点击事件
    @OnClick({R.id.mPlay, R.id.mPause, R.id.mStop})
    public void ClickListener(View view) {
        switch (view.getId()) {
            case R.id.mPlay:
                if (playServiceIt == null) {
                    playServiceIt = new Intent(ctx, PlayService.class);
                }
                if (bundle == null)
                    bundle = new Bundle();
                bundle.putInt("command", PlayService.PlayCode);
                bundle.putString("url", uriStr);
                playServiceIt.putExtras(bundle);
                startService(playServiceIt);
                break;
            case R.id.mPause:
                if (playServiceIt == null) {
                    playServiceIt = new Intent(ctx, PlayService.class);
                }
                if (bundle == null)
                    bundle = new Bundle();
                bundle.putInt("command", PlayService.PauseCode);
                bundle.putString("url", uriStr);
                playServiceIt.putExtras(bundle);
                startService(playServiceIt);
                break;
            case R.id.mStop:
                if (playServiceIt == null) {
                    playServiceIt = new Intent(ctx, PlayService.class);
                }
                if (bundle == null)
                    bundle = new Bundle();
                bundle.putInt("command", PlayService.StopCode);
                bundle.putString("url", uriStr);
                playServiceIt.putExtras(bundle);
                startService(playServiceIt);
                break;

        }
    }


}
