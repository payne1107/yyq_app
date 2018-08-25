package com.yyq58.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yyq58.R;
import com.yyq58.activity.adapter.TalentTypeAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.utils.SPUtil;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.autolayout.AutoLinearLayout;

import static android.app.Activity.RESULT_OK;

public class Fragment1 extends BaseFragment implements View.OnClickListener {
    private static final int REQUEST_CODE_PICK_CITY = 1;
    private TextView tvChooseCity;
    private String city;
    private View mRootView;
    private ImageView ivClose;
    private ImageView ivOpen;
    private AutoLinearLayout layoutYR;
    private TextView tvSeachYR;
    private TextView tvYRDynamic;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment1, null);
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
        initView();
    }

    private void initView() {
        tvChooseCity = mRootView.findViewById(R.id.tv_choose_city);
        ivClose = mRootView.findViewById(R.id.iv_close);
        ivOpen = mRootView.findViewById(R.id.iv_open);
        layoutYR = mRootView.findViewById(R.id.layout_yr);
        tvSeachYR = mRootView.findViewById(R.id.tv_search_yr);
        tvYRDynamic = mRootView.findViewById(R.id.tv_yr_dynamic);

        TabLayout tabLayout = mRootView.findViewById(R.id.tabLayout);
        ViewPager viewPager = mRootView.findViewById(R.id.viewpager);
        TalentTypeAdapter adapter = new TalentTypeAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setListener();
    }

    private void setListener() {
        tvChooseCity.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        ivOpen.setOnClickListener(this);
        tvYRDynamic.setOnClickListener(this);
        tvSeachYR.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_city:
                //select citys
                startActivityForResult(new Intent(getActivity(),CityPickerActivity.class),REQUEST_CODE_PICK_CITY);
                break;
            case R.id.iv_open:
                layoutYR.setVisibility(View.VISIBLE);
                ivClose.setVisibility(View.VISIBLE);
                ivOpen.setVisibility(View.GONE);
                break;
            case R.id.iv_close:
                layoutYR.setVisibility(View.GONE);
                ivClose.setVisibility(View.GONE);
                ivOpen.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_yr_dynamic:
                Toast.makeText(getActivity(), "1233333333333", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_search_yr:
                Toast.makeText(getActivity(), "1233333333333", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode ==  RESULT_OK) {
            if (data != null) {
                city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                SPUtil.putString(getActivity(), "current_city", city);
                tvChooseCity.setText(city);
                //选择城市后将当前选择城市赋值
                MyApplication.currentCity = city;
            }
        }
    }
}
