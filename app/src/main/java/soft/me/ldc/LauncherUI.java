package soft.me.ldc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.adapter.LauncherUIViewPagerAdapter;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.layout.Main1Fragment;
import soft.me.ldc.layout.Main2Fragment;
import soft.me.ldc.layout.Main3Fragment;
import soft.me.ldc.view.GRToastView;

public class LauncherUI extends RootActivity {


    @BindView(R.id.mToolbar)
    Toolbar mToolbar;
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
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    //toolbar 设置
    private ActionBarDrawerToggle mDrawerToggle;
    // 页面
    List<Fragment> fragments = null;
    List<String> titles = null;
    LauncherUIViewPagerAdapter uiViewPagerAdapter = null;

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.launcherui;
    }


    @Override
    protected void Main() {
        initToolbar();
        switchBtn.setOnCheckedChangeListener(new switchBtnListener());//切换事件
        initTabLayout();

    }


    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }

    // TODO: 2018/1/11 标题栏
    private void initToolbar() {
        {
            mToolbar.setTitle("首页");
            mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //动画
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(mDrawerLayout);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(mDrawerLayout);
            }
        };
        mDrawerToggle.syncState();//同步状态
        mDrawerLayout.addDrawerListener(mDrawerToggle); //设置侧滑监听
    }

    // TODO: 2018/1/12
    private void initTabLayout() {
        if (titles == null)
            titles = new ArrayList<>();
        if (fragments == null)
            fragments = new ArrayList<>();
        //标题
        titles.add("发现");
        titles.add("音乐");
        titles.add("我的");
        //页面
        fragments.add(new Main1Fragment());
        fragments.add(new Main2Fragment());
        fragments.add(new Main3Fragment());

        if (uiViewPagerAdapter == null)
            uiViewPagerAdapter = new LauncherUIViewPagerAdapter(fragmentManager);
        uiViewPagerAdapter.pushData(fragments, titles);
        //
        viewPager.setAdapter(uiViewPagerAdapter);
        viewPager.setCurrentItem(0);
        //
        tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.addOnTabSelectedListener(new TabLayoutListener());
    }


    // TODO: 2018/1/12 选择系统
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

    class TabLayoutListener implements TabLayout.OnTabSelectedListener {


        @Override
        public void onTabSelected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            //没有选中
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            //再次选择
        }
    }

}
