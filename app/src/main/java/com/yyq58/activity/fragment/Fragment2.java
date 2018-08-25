package com.yyq58.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yyq58.R;
import com.yyq58.activity.adapter.Fragment2Adapter;
import com.yyq58.activity.base.BaseFragment;
public class Fragment2 extends BaseFragment {
    private View mRootView;
    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment2, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setInVisibleTitleIcon("历史记录", true, false);
        initView();
    }

    private void initView() {
        TabLayout tabLayout = mRootView.findViewById(R.id.tabLayout);
        ViewPager viewPager = mRootView.findViewById(R.id.viewpager);
        Fragment2Adapter adapter = new Fragment2Adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setListener();
    }

    private void setListener() {

    }
}
