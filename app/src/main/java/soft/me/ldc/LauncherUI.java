package soft.me.ldc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.me.ldc.adapter.LauncherUIViewPagerAdapter;
import soft.me.ldc.animotion.ZoomOutPageTransformer;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.layout.Main3Fragment;
import soft.me.ldc.layout.MusicFragment;
import soft.me.ldc.layout.QueryMusicFragment;
import soft.me.ldc.layout.RadioStationFragment;
import soft.me.ldc.thread.service.MultiThreadService;
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
    //
    Intent multiTSIt = null;
    //页面
    List<Fragment> fragments = null;
    //标题
    List<String> titles = null;
    LauncherUIViewPagerAdapter pagerAdapter = null;
    volatile int ScrollPosition = 0;

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
        //

        tabTitle.setBackgroundColor(Color.parseColor("#303F9F"));
        tabTitle.setGravity(Gravity.CENTER);
        tabTitle.setDrawFullUnderline(false);//是否显示下划线
        tabTitle.setTabIndicatorColor(Color.parseColor("#FF4081"));//指示器颜色
        tabTitle.setTextColor(Color.parseColor("#ffffff"));
        tabTitle.setTextSize(0, 50);
        tabTitle.setTextSpacing(10);
        // TODO: 2018/1/20  持久层任务//多线程
        startService(multiTSIt);
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
        }

        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.addOnPageChangeListener(new PagerViewListener());
        mViewPager.setAdapter(pagerAdapter);
        switchBtn.setOnCheckedChangeListener(new switchBtnListener());//切换事件


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

    //页面滑动
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
    // TODO: 2018/1/24 生命周期


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (multiTSIt != null)
            stopService(multiTSIt);
    }
}
