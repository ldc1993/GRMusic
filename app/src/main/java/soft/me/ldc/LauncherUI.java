package soft.me.ldc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.layout.StyleThemeActivity;
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
    @BindView(R.id.switchStyle)
    AppCompatTextView switchStyle;
    @BindView(R.id.mRelativeLayout)
    RelativeLayout mRelativeLayout;
    //
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


    // TODO: 2018/1/11 点击事件
    @OnClick({R.id.switchStyle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switchStyle:
                Intent styleIt = new Intent();
                styleIt.setClass(ctx, StyleThemeActivity.class);
                startActivity(styleIt);
                break;
        }
    }


}
