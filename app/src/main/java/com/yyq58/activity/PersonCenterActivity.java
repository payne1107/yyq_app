package com.yyq58.activity;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.adapter.PersonCenterAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.PersonDetailsBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

public class PersonCenterActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSet;
    private String userId;
    private CircleImageView ivAvatar;
    private TextView tvUsername;
    private TextView tvAttentionNum;
    private TextView tvCategory;
    private TextView tvFansNum;
    private ImageView ivSex;
    private ViewPager viewPager;
    private AutoLinearLayout layoutMotifyPersonInfo;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_person_center);
        mContext = PersonCenterActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("", true, true);
        userId = getIntent().getStringExtra("userId");
        tvSet = findViewById(R.id.activity_set);
        tvSet.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_nav_share);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvSet.setCompoundDrawables(null, null, drawable, null);
        ivAvatar = findViewById(R.id.circleImageView);
        tvUsername = findViewById(R.id.tv_username);
        tvCategory = findViewById(R.id.tv_work_category);
        tvAttentionNum = findViewById(R.id.tv_attention_num);
        tvFansNum = findViewById(R.id.tv_fans_num);
        ivSex = findViewById(R.id.iv_sex);
        layoutMotifyPersonInfo = findViewById(R.id.layout_motify_person_info);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        PersonCenterAdapter adapter = new PersonCenterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setListener();
    }

    private void setListener() {
        layoutMotifyPersonInfo.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 3) {
                    layoutMotifyPersonInfo.setVisibility(View.VISIBLE);
                } else {
                    layoutMotifyPersonInfo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryPersonDetails(userId);
    }

    /***
     * 根据userid查询用户信息
     * @param userid
     */
    private void queryPersonDetails(String userid) {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", userid);
        httpPostRequest(ConfigUtil.QUERY_PERSON_INFO_URL, params, ConfigUtil.QUERY_PERSON_INFO_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PERSON_INFO_URL_ACTION:
                //查询个人信息
                handleQuereyPersonInfo(json);
                break;
        }
    }

    /***
     * 个人信息
     * @param json
     */
    private void handleQuereyPersonInfo(String json) {
        PersonDetailsBean bean = JSON.parseObject(json, PersonDetailsBean.class);
        if (bean != null) {
            PersonDetailsBean.DataBean data = bean.getData();
            if (data != null) {
                String account = data.getAccount();
                String avatar =data.getAvatar();
                String sex = data.getSex();
                String fansNum = data.getFansNums();//粉丝
                String attendNums = data.getAttentionNums();//我关注的人
                String motto = data.getMotto();//心情

                tvUsername.setText(StringUtils.isEmpty(account) ? "" : account);
                tvCategory.setText(StringUtils.isEmpty(motto) ? "" : motto);
                if (!StringUtils.isEmpty(avatar)) {
                    MyApplication.imageLoader.displayImage(avatar, ivAvatar);
                }
                tvFansNum.setText(StringUtils.isEmpty(fansNum) ? "" : fansNum);
                tvAttentionNum.setText(StringUtils.isEmpty(attendNums) ? "" : attendNums);
                if (sex.equals("1")) {
                    ivSex.setImageResource(R.mipmap.icon_male);
                } else {
                    ivSex.setImageResource(R.mipmap.icon_female);
                }
            }
        }
    }

    /***
     * 用户id
     * @return
     */
    public String getUserId() {
        return userId;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_motify_person_info:
                //修改个人资料
                startActivity(new Intent(mContext, MotifyPersonDetailsActivity.class));
                break;
        }
    }
}
