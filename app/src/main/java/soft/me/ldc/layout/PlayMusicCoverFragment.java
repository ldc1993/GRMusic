package soft.me.ldc.layout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMusicCoverFragment extends RootFragment {
    volatile PlayMusicSongBean mData = null;
    @BindView(R.id.song_icon)
    AppCompatImageView micon;
    @BindView(R.id.title)
    AppCompatTextView title;
    @BindView(R.id.author)
    AppCompatTextView author;

    //构造函数
    public void pushData(PlayMusicSongBean mData) {
        this.mData = mData;
    }

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        View mainView = inflater.inflate(R.layout.fragment_play_music_cover, null, false);
        mainView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return mainView;
    }

    @Override
    protected void Init() throws Exception {
        if (StringUtil.isNotBlank(mData.songinfo.pic_big))
            Picasso.with(ctx).load(mData.songinfo.pic_big).resize(90, 90).centerCrop().onlyScaleDown().into(micon);
        title.setText(mData.songinfo.title);
        author.setText(mData.songinfo.author);

    }

    @Override
    protected void Main() throws Exception {
    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


}
