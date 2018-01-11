package soft.me.ldc.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by LDC on 2017/7/31.
 */

public class RootPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = null;
    private Context ctx = null;

    public RootPagerAdapter(FragmentManager fm, Context ctx, ArrayList<Fragment> fragments) {
        super(fm);
        this.ctx = ctx;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
