package com.yyq58.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.yyq58.activity.fragment.AnchorFragment;
import com.yyq58.activity.fragment.DanceFragment;
import com.yyq58.activity.fragment.DirectFragment;
import com.yyq58.activity.fragment.ModelFragmnet;
import com.yyq58.activity.fragment.NewestFragNment;

public class TalentTypeAdapter extends FragmentPagerAdapter {
    private String[] mTitle = new String[]{"最新","舞蹈","模特","主播","主持"};
    public TalentTypeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewestFragNment();
        } else if (position == 1) {
            return new DanceFragment();
        } else if (position == 2) {
            return new ModelFragmnet();
        } else if (position == 3) {
            return new AnchorFragment();
        } else {
            return new DirectFragment();
        }
    }

    @Override
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
