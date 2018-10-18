package com.yyq58.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.yyq58.activity.fragment.AnchorYRFragment;
import com.yyq58.activity.fragment.DanceYRFragment;
import com.yyq58.activity.fragment.ModuleYRFragmnet;
import com.yyq58.activity.fragment.PresideOverYRFragment;
import com.yyq58.activity.fragment.NewYRFragment;

public class SearchYRViewPagerAdapter  extends FragmentPagerAdapter {
    private String[] mTitle = new String[]{"最新","舞蹈","模特","主播","主持"};
    public SearchYRViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewYRFragment();
        } else if (position == 1) {
            return new DanceYRFragment();
        } else if (position == 2) {
            return new ModuleYRFragmnet();
        } else if (position == 3) {
            return new AnchorYRFragment();
        } else {
            return new PresideOverYRFragment();
        }
    }

    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
