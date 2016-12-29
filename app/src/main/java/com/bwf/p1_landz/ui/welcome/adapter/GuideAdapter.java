package com.bwf.p1_landz.ui.welcome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwf.p1_landz.ui.welcome.fragment.GuideFragment;

/**
 * Created by Cao_Ye on 2016/11/29.
 */
public class GuideAdapter extends FragmentPagerAdapter{
    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        GuideFragment guideFragment = GuideFragment.newInstance(position);
        return guideFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
