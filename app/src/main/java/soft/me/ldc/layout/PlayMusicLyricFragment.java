package soft.me.ldc.layout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMusicLyricFragment extends RootFragment {

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

    }
}
