package soft.me.ldc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liudi on 2018/1/12.
 */

public class LauncherUIViewPagerAdapter extends FragmentPagerAdapter {
    List<String> titles = null;
    List<Fragment> fragments = null;


    public LauncherUIViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void pushData(List<Fragment> fragments, List<String> titles) {
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles != null && fragments != null ? titles.size() >= fragments.size() ? fragments.size() : titles.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
