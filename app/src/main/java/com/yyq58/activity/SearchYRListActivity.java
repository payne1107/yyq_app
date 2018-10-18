package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.yyq58.R;
import com.yyq58.activity.adapter.SearchYRViewPagerAdapter;
import com.yyq58.activity.base.BaseActivity;

public class SearchYRListActivity extends BaseActivity {
    private Context mContext;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_yr_list);
        mContext = SearchYRListActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("最新艺人", true, true);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager =  findViewById(R.id.viewpager);
        SearchYRViewPagerAdapter adapter = new SearchYRViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
