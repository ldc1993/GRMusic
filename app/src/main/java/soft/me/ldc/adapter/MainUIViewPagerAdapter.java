package soft.me.ldc.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liudi on 2018/1/12.
 */

public class MainUIViewPagerAdapter extends FragmentPagerAdapter {
    Context ctx = null;
    List<Fragment> fragments = null;
    List<String> titles = null;


    public MainUIViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void pushData(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void pustTitle(List<String> titles) {
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
