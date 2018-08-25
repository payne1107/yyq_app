package com.yyq58.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.yyq58.activity.fragment.DangQiFragment;
import com.yyq58.activity.fragment.DynamicFragNment;
import com.yyq58.activity.fragment.InformationFragment;
import com.yyq58.activity.fragment.ProductionFragmnet;

public class PersonCenterAdapter extends FragmentPagerAdapter {
    private String[] mTitle = new String[]{"动态","档期","作品","资料"};
    public PersonCenterAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DynamicFragNment();
        } else if (position == 1) {
            return new DangQiFragment();
        } else if (position == 2) {
            return new ProductionFragmnet();
        }  else {
            return new InformationFragment();
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
