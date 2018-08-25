package com.yyq58.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.adapter.PersonCenterAdapter;
import com.yyq58.activity.base.BaseActivity;

public class PersonCenterActivity extends BaseActivity{

    private TextView tvSet;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_person_center);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("", true, true);
        tvSet = findViewById(R.id.activity_set);
        tvSet.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_nav_share);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvSet.setCompoundDrawables(null, null, drawable, null);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        PersonCenterAdapter adapter = new PersonCenterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setListener();
    }

    private void setListener() {

    }
}
