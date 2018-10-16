package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yyq58.R;
import com.yyq58.activity.adapter.CheckBigImgAdapter;
import com.yyq58.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 92457 on 2018/3/9.
 * 查看大图
 */

public class CheckBigImgActivity extends BaseActivity {
    private ViewPager viewPager;
    private CheckBigImgAdapter adapter;
    private Context mContext;
    private List<Object> mList = new ArrayList<>();
    private int currentPage = 1;//当前页数
    private TextView tvTotalPage;
    private TextView tvCurrentPage;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_check_big_img);
        mContext = CheckBigImgActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        String jsonImgs = getIntent().getStringExtra("jsonImgs");
        Log.d("Dong", "------------------>" + jsonImgs);
        JSONArray jsonArray = JSON.parseArray(jsonImgs);
        for (int i = 0; i < jsonArray.size(); i++) {
            mList.add(jsonArray.get(i));
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tvTotalPage = (TextView) findViewById(R.id.textView2);
        tvCurrentPage = (TextView) findViewById(R.id.tv_current_page);
        tvTotalPage.setText(mList.size() + "");
        adapter = new CheckBigImgAdapter(mList, mContext, CheckBigImgActivity.this);
        viewPager.setAdapter(adapter);
        setListener();
    }



    private void setListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage = position + 1;
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                tvCurrentPage.setText(currentPage + "");
            }
        });
    }
}
