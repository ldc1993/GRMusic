package soft.me.ldc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import soft.me.ldc.adapter.LauncherUIViewPagerAdapter;
import soft.me.ldc.animotion.DepthPageTransformer;
import soft.me.ldc.animotion.ZoomOutPageTransformer;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.layout.Main3Fragment;
import soft.me.ldc.layout.MusicFragment;
import soft.me.ldc.layout.QueryMusicActivity;
import soft.me.ldc.layout.RadioStationFragment;
import soft.me.ldc.thread.service.MultiThreadService;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class LauncherUI extends RootActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
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
    @BindView(R.id.musicbar)
    LinearLayoutCompat musicbar;
    //
    Intent multiTSIt = null;
    //页面
    List<Fragment> fragments = null;
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
        if (multiTSIt == null)
            multiTSIt = new Intent(ctx, MultiThreadService.class);
        // TODO: 2018/1/20  持久层任务//多线程
        startService(multiTSIt);
        initToolbar();
        {
            if (fragments == null)
                fragments = new ArrayList<>();
            fragments.clear();

            fragments.add(new MusicFragment());
            fragments.add(new RadioStationFragment());
            fragments.add(new Main3Fragment());
            if (pagerAdapter == null)
                pagerAdapter = new LauncherUIViewPagerAdapter(fragmentManager);
            pagerAdapter.pushData(fragments);
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

    // TODO: 2018/1/11 标题栏
    private void initToolbar() {
        {
            mToolbar.setLeftImg(R.mipmap.menu_icon);
            mToolbar.setTitleText("音乐播放器");
            mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        mToolbar.setLeftImg(R.mipmap.menu_icon);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.LEFT);
                        mToolbar.setLeftImg(R.mipmap.back_icon);
                    }
                }
            });
            mToolbar.setRightImg(R.mipmap.search_icon);
            mToolbar.setRightBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent();
                    it.setClass(ctx, QueryMusicActivity.class);
                    startActivity(it);
                }
            });
            setSupportActionBar(mToolbar);
        }
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
                    mToolbar.setTitleText("发 现");
                    break;
                case 1:
                    mToolbar.setTitleText("电 台");
                    break;
                case 2:
                    mToolbar.setTitleText("我 的");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (multiTSIt != null)
            stopService(multiTSIt);
    }
}
