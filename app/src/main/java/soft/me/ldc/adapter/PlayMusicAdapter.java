package soft.me.ldc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liudi on 2018/2/10.
 */

public class PlayMusicAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = null;

    public void pushData(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public PlayMusicAdapter(FragmentManager fm) {
        super(fm);
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
