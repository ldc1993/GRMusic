package soft.me.ldc;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.adapter.MainUIViewPagerAdapter;
import soft.me.ldc.adapter.viewholder.MainUIMenuListAdapter;
import soft.me.ldc.ali.LocLocation;
import soft.me.ldc.animotion.ZoomOutPageTransformer;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.layout.AboutActivity;
import soft.me.ldc.layout.LocalMusicFragment;
import soft.me.ldc.layout.MusicFragment;
import soft.me.ldc.layout.PlayMusicActivity;
import soft.me.ldc.layout.QueryMusicFragment;
import soft.me.ldc.layout.RadioStationFragment;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.common.service.MultiThreadService;
import soft.me.ldc.model.WeatherBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.permission.PermissionIface;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.utils.NetUtil;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRToastView;

public class MainUI extends RootActivity {


    @BindView(R.id.mImage)
    AppCompatImageView mImage;
    @BindView(R.id.menuList)
    RecyclerView menuList;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.mRelativeLayout)
    RelativeLayout mRelativeLayout;
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
    MainUIViewPagerAdapter pagerAdapter = null;
    //滑动位置
    volatile int ScrollPosition = 0;
    //
    GetPlayMusicTask getPlayMusicTask = null;
    //
    GetWeatherTask getWeatherTask = null;
    //
    Bundle bundle = null;
    //定位
    LocLocation locLocation = null;
    //
    volatile PlayMusicSongBean mData = null;
    //
    MainUIMenuListAdapter mainUIMenuListAdapter = null;
    String[] menus = new String[]{"更新天气", "about", "退出"};
    LinearLayoutManager llm = null;
    //
    //TODO: 需要的动态权限
    String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            //Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.CAMERA//拍照
    };
    //消息
    Message msg = null;
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
                    if (playService.HasEnable() && mData != null) {
                        if (playService.IsPlaying()) {
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
        ActivityList.addActivity(this);
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_main_ui;
    }


    @Override
    protected void Main() {
        //音乐后台服务
        if (multiTSIt == null)
            multiTSIt = new Intent(ctx, MultiThreadService.class);
        // TODO: 2018/1/20  持久层任务//多线程
        startService(multiTSIt);
        if (Build.VERSION.SDK_INT >= 23) {
            requestRunTimePermission(permissions, new RunTimePermission());
        }
        //定位实例化
        {
            locLocation = LocLocation.Instance(ctx);
        }
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
            if (llm == null)
                llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
            if (mainUIMenuListAdapter == null)
                mainUIMenuListAdapter = new MainUIMenuListAdapter();
            mainUIMenuListAdapter.pushData(Arrays.asList(menus));
            mainUIMenuListAdapter.setOnItemListener(new MenuItemListener());

            menuList.setLayoutManager(llm);
            menuList.setLayoutFrozen(true);
            menuList.setHasFixedSize(true);
            menuList.setAdapter(mainUIMenuListAdapter);

        }
        {
            if (fragments == null)
                fragments = new ArrayList<>();
            fragments.clear();
            if (titles == null)
                titles = new ArrayList<>();
            titles.clear();
            //添加页面
            fragments.add(new LocalMusicFragment());
            fragments.add(new MusicFragment());
            fragments.add(new RadioStationFragment());
            fragments.add(new QueryMusicFragment());
            //添加标题
            titles.add("我的音乐");
            titles.add("发现");
            titles.add("电台");
            titles.add("音乐搜索");

            if (pagerAdapter == null)
                pagerAdapter = new MainUIViewPagerAdapter(fragmentManager);
            pagerAdapter.pushData(fragments);
            pagerAdapter.pustTitle(titles);
            //
            mViewPager.setCurrentItem(0);
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            mViewPager.addOnPageChangeListener(new PagerViewListener());
            mViewPager.setAdapter(pagerAdapter);
            mViewPager.setOffscreenPageLimit(3);//预加载界面
        }
        //显示播放
        dkhandler.sendEmptyMessage(GetPlayMusicCode);
        //获取天气
        RunGetWeatherTask();
    }


    //点击事件
    @OnClick({R.id.playList, R.id.playOrpause, R.id.playNext, R.id.playBar})
    public void ClickListener(View view) {
        switch (view.getId()) {
            case R.id.playList:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.playOrpause:
                if (playService.HasEnable() && playService.HasData()) {
                    if (playService.IsPlaying()) {
                        playService.Pause();
                        playOrpause.setImageResource(R.drawable.ic_play_bar_btn_play);
                    } else {
                        playService.Start();
                        playOrpause.setImageResource(R.drawable.ic_play_bar_btn_pause);
                    }
                }

                break;
            case R.id.playNext:
                NetUtil.NetSetting(ctx);
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

    //菜单列表item listener
    class MenuItemListener implements MainUIMenuListAdapter.onItemListener {

        @Override
        public void Click(View v, int position) {
            switch (position) {
                case 0:
                    RunGetWeatherTask();
                    break;
                case 1:
                    startActivity(new Intent(ctx, AboutActivity.class));
                    break;
                case 2:
                    QuitDialog();
                    break;
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
                bean = playService.MusicSrc();
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

    //执行获取地位 天气清空
    private void RunGetWeatherTask() {
        if (getWeatherTask != null && !getWeatherTask.isCancelled()) {
            getWeatherTask.cancel(true);
        }
        getWeatherTask = new GetWeatherTask();
        getWeatherTask.execute();

    }

    //获取天气
    class GetWeatherTask extends AsyncTask<Void, Void, WeatherBean> {


        @Override
        protected WeatherBean doInBackground(Void... voids) {
            WeatherBean weatherBean = null;
            try {
                Gson gson = new Gson();
                String adCode = locLocation.getAdCode();
                GRToastView.show(ctx, adCode, Toast.LENGTH_SHORT);
                String str = HttpService.Instance(ctx).Weather(adCode + "");
                weatherBean = gson.fromJson(str, WeatherBean.class);
            } catch (Exception e) {
                dkhandler.sendEmptyMessage(ErrorCode);
                e.printStackTrace();
            }
            return weatherBean;
        }

        @Override
        protected void onPostExecute(WeatherBean result) {
            super.onPostExecute(result);

        }
    }

    // TODO: 2017/11/20 请求运行时动态权限
    class RunTimePermission implements PermissionIface {

        @Override
        public void onGranted() {
            // TODO: 2017/11/20 授权成功
        }

        @Override
        public void onDenied(List<String> deniedPermissions) {
            StringBuilder sb = new StringBuilder();
            sb.delete(0, sb.length());
            for (String p : deniedPermissions) {
                sb.append(p + "\n");
            }
            GRToastView.show(ctx, "拒绝:" + sb.toString(), Toast.LENGTH_SHORT);
        }
    }


    //后台程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    //退出提示
    private void QuitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("温馨提示!");
        builder.setItems(new String[]{"后台休息", "残忍退出"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        moveTaskToBack(false);
                        break;
                    case 1:
                        playService.Stop();
                        ActivityList.killAllActivity();
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }

            }
        });
        builder.setCancelable(true);
        builder.create();
        builder.show();

    }


    // TODO: 2018/1/24 生命周期


    @Override
    protected void onStart() {
        super.onStart();
        locLocation.StartLocation();
    }

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
        //结束服务
        if (multiTSIt != null) {
            stopService(multiTSIt);
        }
        //销毁
        if (locLocation != null) {
            locLocation.DestroyLocation();
        }
    }


}
