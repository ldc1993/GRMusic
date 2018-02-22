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
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.adapter.MainUIViewPagerAdapter;
import soft.me.ldc.adapter.viewholder.MainUIMenuListAdapter;
import soft.me.ldc.ali.AliLocInfo;
import soft.me.ldc.ali.AliLocLocation;
import soft.me.ldc.animotion.ZoomOutPageTransformer;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.common.service.MultiThreadService;
import soft.me.ldc.layout.AboutActivity;
import soft.me.ldc.layout.LocalMusicFragment;
import soft.me.ldc.layout.MusicFindFragment;
import soft.me.ldc.layout.PlayMusicMusicActivity;
import soft.me.ldc.layout.QueryMusicFragment;
import soft.me.ldc.layout.RadioStationFragment;
import soft.me.ldc.layout.SongerListFragment;
import soft.me.ldc.model.LocalMusicBean;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.model.WeatherBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.permission.PermissionIface;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.utils.MusicManager;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRToastView;

public class MainUI extends RootMusicActivity {


    @BindView(R.id.mWeather)
    AppCompatTextView mWeather;
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
    @BindView(R.id.appVersion)
    AppCompatTextView appVersion;
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
    RefreshPlayStateTask refreshPlayStateTask = null;
    //定位
    AliLocLocation aliLocLocation = null;
    GetWeatherTask getWeatherTask = null;
    volatile AliLocInfo aliLocInfo = null;
    //此一次定位
    volatile boolean isFirstLoc = true;
    //
    Bundle bundle = null;
    //
    volatile PlayMusicSongBean mData = null;
    //
    MainUIMenuListAdapter mainUIMenuListAdapter = null;
    String[] menus = new String[]{"关于", "退出"};
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

    final int ErrorCode = 0x000;
    final int SuccessCode = 0x001;
    final int GetPlayMusicCode = 0x002;
    final int RefreshWeatherCode = 0x0003;
    final int UpdateweatherDataCode = 0x004;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GetPlayMusicCode:
                    RunPlayStateTask();
                    break;
                case SuccessCode:
                    mData = (PlayMusicSongBean) msg.obj;
                    //更新播放图标
                    refreshPlayMusic(mData);
                    //更新播放图标
                    if (playService != null && playService.HasEnable() && playService.HasData()) {
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
                case RefreshWeatherCode://更新天气
                    RunGetWeatherTask((AliLocInfo) msg.obj);
                    break;
                case UpdateweatherDataCode:
                    StringBuffer sb = new StringBuffer();
                    WeatherBean weatherBean = (WeatherBean) msg.obj;
                    if (weatherBean != null && weatherBean.lives != null && weatherBean.lives.size() > 0) {
                        WeatherBean.LivesBean bean = weatherBean.lives.get(0);
                        sb.append(bean.province + "-" + bean.city + "\n");
                        sb.append(bean.weather + "-" + bean.temperature + "℃\n");
                        sb.append("风向:" + bean.winddirection + "-" + bean.windpower + "级\n");
                        sb.append(bean.reporttime);
                        mWeather.setText(sb.toString());
                    }
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
        // TODO: 2018/2/16 定位
        {
            aliLocLocation = AliLocLocation.Instance(ctx);
            aliLocLocation.setListener(new aliLocationListener());
            aliLocLocation.StartLocation();
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
            //版本
            appVersion.setText("" + BuildConfig.VERSION_NAME);

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
            fragments.add(new SongerListFragment());
            fragments.add(new MusicFindFragment());
            fragments.add(new RadioStationFragment());
            fragments.add(new QueryMusicFragment());
            //添加标题
            titles.add("我的音乐");
            titles.add("歌手");
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
            mViewPager.setOffscreenPageLimit(4);//预加载界面
        }
        //显示播放
        dkhandler.sendEmptyMessage(GetPlayMusicCode);

    }


    //点击事件
    @OnClick({R.id.playList, R.id.playOrpause, R.id.playNext, R.id.playBar, R.id.appVersion})
    public void ClickListener(View view) {
        switch (view.getId()) {
            case R.id.playList:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.playOrpause:
                if (playService != null && playService.HasEnable() && playService.HasData()) {
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
                // TODO: 2018/2/15 随机播放
                try {
                    LocalMusicBean locMusic = MusicManager.Instance(ctx).RandomMusic();
                    if (playService != null && playService.HasEnable()) {
                        if (locMusic != null) {
                            PlayMusicSongBean tempPlay = new PlayMusicSongBean();
                            tempPlay.songinfo.title = locMusic.title;
                            tempPlay.songinfo.author = locMusic.author;
                            tempPlay.bitrate.show_link = locMusic.path;
                            playService.Play(tempPlay);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    RunPlayStateTask();
                }
                break;
            case R.id.playBar:// TODO: 2018/2/15 播放界面
                if (mData != null) {
                    bundle = new Bundle();
                    bundle.putSerializable("play", mData);
                    bundle.putBoolean("play_new_song", false);
                    Intent it = new Intent(ctx, PlayMusicMusicActivity.class);
                    it.putExtras(bundle);
                    startActivity(it);
                } else {
                    GRToastView.show(ctx, "没有可播放音乐哦", Toast.LENGTH_SHORT);
                }
                break;
            case R.id.appVersion:
                GRToastView.show(ctx, "" + BuildConfig.VERSION_NAME, Toast.LENGTH_SHORT);
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
                    startActivity(new Intent(ctx, AboutActivity.class));
                    break;
                case 1:
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
    private void RunPlayStateTask() {
        if (refreshPlayStateTask != null && !refreshPlayStateTask.isCancelled()) {
            refreshPlayStateTask.cancel(true);
        }
        refreshPlayStateTask = new RefreshPlayStateTask();
        refreshPlayStateTask.execute();

    }

    //任务
    class RefreshPlayStateTask extends AsyncTask<Void, Void, PlayMusicSongBean> {

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

    //执行获取地位更新天气数据
    private void RunGetWeatherTask(AliLocInfo aliLocInfo) {
        if (getWeatherTask != null && !getWeatherTask.isCancelled()) {
            getWeatherTask.cancel(true);
        }
        getWeatherTask = new GetWeatherTask();
        getWeatherTask.pushData(aliLocInfo);
        getWeatherTask.execute();

    }

    //获取天气
    class GetWeatherTask extends AsyncTask<Void, Void, WeatherBean> {
        AliLocInfo aliLocInfo = null;

        public void pushData(AliLocInfo aliLocInfo) {
            this.aliLocInfo = aliLocInfo;
        }

        @Override
        protected WeatherBean doInBackground(Void... voids) {
            WeatherBean weatherBean = null;
            try {
                Gson gson = new Gson();
                String adCode = aliLocInfo.AdCode;
                String str = HttpService.INSTANCE.Service(ctx).Weather(adCode + "");
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
            if (result != null) {
                msg = dkhandler.obtainMessage();
                msg.what = UpdateweatherDataCode;
                msg.obj = result;
                dkhandler.sendMessage(msg);
            }

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

    // TODO: 2018/2/16 定位监听
    class aliLocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    aliLocInfo = new AliLocInfo();
                    aliLocInfo.LocationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    aliLocInfo.Latitude = amapLocation.getLatitude();//获取纬度
                    aliLocInfo.Longitude = amapLocation.getLongitude();//获取经度
                    aliLocInfo.Accuracy = amapLocation.getAccuracy();//获取精度信息
                    aliLocInfo.Address = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aliLocInfo.Country = amapLocation.getCountry();//国家信息
                    aliLocInfo.Province = amapLocation.getProvince();//省信息
                    aliLocInfo.City = amapLocation.getCity();//城市信息
                    aliLocInfo.District = amapLocation.getDistrict();//城区信息
                    aliLocInfo.Street = amapLocation.getStreet();//街道信息
                    aliLocInfo.StreetNum = amapLocation.getStreetNum();//街道门牌号信息
                    aliLocInfo.CityCode = amapLocation.getCityCode();//城市编码
                    aliLocInfo.AdCode = amapLocation.getAdCode();//地区编码
                    aliLocInfo.AoiName = amapLocation.getAoiName();//获取当前定位点的AOI信息
                    aliLocInfo.BuildingId = amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    aliLocInfo.Floor = amapLocation.getFloor();//获取当前室内定位的楼层
                    aliLocInfo.GpsAccuracyStatus = amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //第一次定位
                    if (isFirstLoc) {
                        //更新天气
                        dkhandler.sendMessage(dkhandler.obtainMessage(RefreshWeatherCode, aliLocInfo));
                        isFirstLoc = false;
                    }
                } else {
                    Log.e("", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
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
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束服务
        if (multiTSIt != null) {
            stopService(multiTSIt);
        }
        //销毁
        if (aliLocLocation != null) {
            aliLocLocation.DestroyLocation();
        }
    }
}
