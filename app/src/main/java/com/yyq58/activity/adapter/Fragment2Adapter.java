package com.yyq58.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.yyq58.activity.fragment.AnnunciateFragNment;
import com.yyq58.activity.fragment.QiangDanFragmnet;
import com.yyq58.activity.fragment.QiuDanFragment;

public class Fragment2Adapter extends FragmentPagerAdapter {
    private String[] mTitle = new String[]{"通告", "求单", "抢单"};

    public Fragment2Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AnnunciateFragNment();
        } else if (position == 1) {
            return new QiuDanFragment();
        } else{
            return new QiangDanFragmnet();
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
