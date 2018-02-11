package soft.me.ldc.layout;


import android.os.Bundle;
import android.support.annotation.ColorLong;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMusicLyricFragment extends RootFragment {

    @BindView(R.id.PingIcon)
    AppCompatImageView PingIcon;

    //构造函数
    public PlayMusicLyricFragment() {

    }

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        View mainView = inflater.inflate(R.layout.fragment_play_music_lyric, null, false);
        mainView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return mainView;
    }

    @Override
    protected void Init() throws Exception {

    }

    @Override
    protected void Main() throws Exception {

    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }

    @OnClick({R.id.PingIcon})
    public void ClickLisener(View view) {
        switch (view.getId()) {
            case R.id.PingIcon:
                GRToastView.show(ctx, "这是一只猪!!", Toast.LENGTH_SHORT);
                break;
        }
    }
}
