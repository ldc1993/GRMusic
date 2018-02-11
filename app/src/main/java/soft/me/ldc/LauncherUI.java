package soft.me.ldc;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.adapter.LauncherUIViewPagerAdapter;
import soft.me.ldc.animotion.ZoomOutPageTransformer;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.layout.Main3Fragment;
import soft.me.ldc.layout.MusicFragment;
import soft.me.ldc.layout.PlayMusicActivity;
import soft.me.ldc.layout.QueryMusicFragment;
import soft.me.ldc.layout.RadioStationFragment;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.common.service.MultiThreadService;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRToastView;

public class LauncherUI extends RootActivity {


    @BindView(R.id.mImage)
    AppCompatImageView mImage;
    @BindView(R.id.menuList)
    RecyclerView menuList;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.mRelativeLayout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.switchText)
    AppCompatTextView switchText;
    @BindView(R.id.switchBtn)
    AppCompatCheckBox switchBtn;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.tab_title)
    PagerTabStrip tabTitle;
    @BindView(R.id.playIcon)
    AppCompatImageView playIcon;
    @BindView(R.id.playTitle)
    AppCompatTextView playTitle;
    @BindView(R.id.playAuthor)
    AppCompatTextView playAuthor;
    @BindView(R.id.playList)
    ImageButton playList;
    @BindView(R.id.playOrpause)
    ImageButton playOrpause;
    @BindView(R.id.playNext)
    ImageButton playNext;
    @BindView(R.id.playBar)
    LinearLayoutCompat playBar;
    //启动多线程意图
    Intent multiTSIt = null;
    //页面
    List<Fragment> fragments = null;
    //标题
    List<String> titles = null;
    LauncherUIViewPagerAdapter pagerAdapter = null;
    //滑动位置
    volatile int ScrollPosition = 0;
    //消息
    Message msg = null;
    //
    GetPlayMusicTask getPlayMusicTask = null;
    //
    Bundle bundle = null;
    //
    volatile PlayMusicSongBean mData = null;
    //
    final static int SuccessCode = 0x001;
    final static int ErrorCode = 0x000;
    final static int GetPlayMusicCode = 0x002;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GetPlayMusicCode:
                    RunPlayMusicTask();
                    break;
                case SuccessCode:
                    mData = (PlayMusicSongBean) msg.obj;

                    refreshPlayMusic(mData);
                    //更新播放图标
                    if (playService.Player() != null && mData != null) {
                        if (playService.Player().isPlaying()) {
                            playOrpause.setImageResource(R.drawable.ic_play_bar_btn_pause);
                        } else {
                            playOrpause.setImageResource(R.drawable.ic_play_bar_btn_play);
                        }
                    }
                    break;
                case ErrorCode:
                    // GRToastView.show(ctx, "获取信息失败", Toast.LENGTH_SHORT);
                    break;
            }
        }
    };

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {


    }

    @Override
    protected Integer UI() {
        return R.layout.launcherui;
    }


    @Override
    protected void Main() {
        //音乐后台服务
        if (multiTSIt == null)
            multiTSIt = new Intent(ctx, MultiThreadService.class);
        // TODO: 2018/1/20  持久层任务//多线程
        startService(multiTSIt);
        //指示器
        {
            tabTitle.setBackgroundColor(Color.parseColor("#F44236"));
            tabTitle.setGravity(Gravity.CENTER_VERTICAL);
            tabTitle.setDrawFullUnderline(true);//是否显示下划线
            tabTitle.setTabIndicatorColor(Color.parseColor("#F44236"));//指示器颜色
            tabTitle.setTextColor(Color.parseColor("#ffffff"));
            tabTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }
        {
            if (fragments == null)
                fragments = new ArrayList<>();
            fragments.clear();
            if (titles == null)
                titles = new ArrayList<>();
            titles.clear();
            //添加页面
            fragments.add(new MusicFragment());
            fragments.add(new RadioStationFragment());
            fragments.add(new QueryMusicFragment());
            fragments.add(new Main3Fragment());
            //添加标题
            titles.add("发现");
            titles.add("电台");
            titles.add("音乐搜索");
            titles.add("我的");

            if (pagerAdapter == null)
                pagerAdapter = new LauncherUIViewPagerAdapter(fragmentManager);
            pagerAdapter.pushData(fragments);
            pagerAdapter.pustTitle(titles);
            //
            mViewPager.setCurrentItem(0);
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            mViewPager.addOnPageChangeListener(new PagerViewListener());
            mViewPager.setAdapter(pagerAdapter);
            mViewPager.setOffscreenPageLimit(3);//预加载界面
            switchBtn.setOnCheckedChangeListener(new switchBtnListener());//切换事件
        }
        //显示播放
        dkhandler.sendEmptyMessage(GetPlayMusicCode);
    }


    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    // TODO: 2018/1/12 选择主题
    class switchBtnListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                switchText.setText("夜间");
                GRToastView.show(ctx, "夜间", Toast.LENGTH_SHORT);
            } else {
                switchText.setText("白天");
                GRToastView.show(ctx, "白天", Toast.LENGTH_SHORT);
            }
        }
    }

    //点击事件
    @OnClick({R.id.playList, R.id.playOrpause, R.id.playNext, R.id.playBar})
    public void ClickListener(View view) {
        switch (view.getId()) {
            case R.id.playList:
                break;
            case R.id.playOrpause:
                if (playService.Player() != null && playService.MusicBean() != null) {
                    if (playService.Player().isPlaying()) {
                        playService.Pause();
                        playOrpause.setImageResource(R.drawable.ic_play_bar_btn_play);
                    } else {
                        playService.Play();
                        playOrpause.setImageResource(R.drawable.ic_play_bar_btn_pause);
                    }
                }

                break;
            case R.id.playNext:
                break;
            case R.id.playBar:
                if (mData != null) {
                    bundle = new Bundle();
                    bundle.putSerializable("play", mData);
                    bundle.putBoolean("play_new_song", false);
                    Intent it = new Intent(ctx, PlayMusicActivity.class);
                    it.putExtras(bundle);
                    startActivity(it);
                } else {
                    GRToastView.show(ctx, "没有可播放音乐哦", Toast.LENGTH_SHORT);
                }
                break;
        }
    }

    //页面滑动监听
    class PagerViewListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            switch (position) {
                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {
            ScrollPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                mViewPager.setCurrentItem(ScrollPosition, false);
            }

        }
    }

    //获取刷新播放数据
    private void refreshPlayMusic(PlayMusicSongBean music) {
        if (music == null)
            return;
        if (StringUtil.isNotBlank(music.songinfo.pic_premium))
            Picasso.with(ctx).load(music.songinfo.pic_premium).resize(500, 500).centerInside().into(playIcon);
        playTitle.setText(music.songinfo.title);
        playAuthor.setText(music.songinfo.author);


    }

    //执行任务
    private void RunPlayMusicTask() {
        if (getPlayMusicTask != null && !getPlayMusicTask.isCancelled()) {
            getPlayMusicTask.cancel(true);
        }
        getPlayMusicTask = new GetPlayMusicTask();
        getPlayMusicTask.execute();

    }

    //任务
    class GetPlayMusicTask extends AsyncTask<Void, Void, PlayMusicSongBean> {

        @Override
        protected PlayMusicSongBean doInBackground(Void... voids) {
            PlayMusicSongBean bean = null;
            try {
                bean = playService.MusicBean();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bean;
        }

        @Override
        protected void onPostExecute(PlayMusicSongBean result) {
            super.onPostExecute(result);
            msg = new Message();
            if (result != null) {
                msg.what = SuccessCode;
                msg.obj = result;
            } else {
                msg.what = ErrorCode;
            }
            dkhandler.sendMessage(msg);
        }
    }

    // TODO: 2018/1/24 生命周期


    @Override
    protected void onResume() {
        super.onResume();
        dkhandler.sendEmptyMessage(GetPlayMusicCode);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dkhandler.sendEmptyMessage(GetPlayMusicCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (multiTSIt != null)
            stopService(multiTSIt);
    }


}
