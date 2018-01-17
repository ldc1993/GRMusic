package soft.me.ldc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.layout.Main3Fragment;
import soft.me.ldc.layout.MusicFragment;
import soft.me.ldc.layout.QueryMusicActivity;
import soft.me.ldc.layout.RadioStationFragment;
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
    BottomNavigationView tabLayout;
    //toolbar 设置
    private ActionBarDrawerToggle mDrawerToggle;

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
        //tab点击点击事件
        SwitchPager(new MusicFragment());//默认显示第一页
        tabLayout.setOnNavigationItemSelectedListener(new TabLayoutListener());
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

    //tabLayout选择事件
    class TabLayoutListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        volatile boolean callback = false;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_item1:
                    callback = true;
                    SwitchPager(new MusicFragment());
                    break;
                case R.id.tab_item2:
                    callback = true;
                    SwitchPager(new RadioStationFragment());
                    break;
                case R.id.tab_item3:
                    callback = true;
                    SwitchPager(new Main3Fragment());
                    break;
                default:
                    callback = false;
                    break;
            }
            return callback;
        }
    }

    // TODO: 2018/1/17 页面切换
    private void SwitchPager(Fragment fragment) {
        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.view_content, fragment).commitNow();
    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xml, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Intent it = new Intent();
                it.setClass(ctx, QueryMusicActivity.class);
                startActivity(it);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
