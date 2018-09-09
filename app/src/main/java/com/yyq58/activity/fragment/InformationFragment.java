package com.yyq58.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.PersonCenterActivity;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.PersonDetailsBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 资料哦
 */
public class InformationFragment extends BaseFragment {

    private TextView tvLocation;
    private TextView tvSex;
    private TextView tvPhone;
    private String userId;
    private TextView tvCategory;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_infomation_layout, null);
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
        tvLocation = mRootView.findViewById(R.id.tv_location);
        tvSex = mRootView.findViewById(R.id.tv_sex);
        tvPhone = mRootView.findViewById(R.id.tv_phone);
        tvCategory = mRootView.findViewById(R.id.tv_category);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        userId  = ((PersonCenterActivity) context).getUserId();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
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
    public void httpOnResponse(String json, int action) {
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
        Log.d("Dong", "-->" + json);
        PersonDetailsBean bean = JSON.parseObject(json, PersonDetailsBean.class);
        if (bean != null) {
            PersonDetailsBean.DataBean data = bean.getData();
            if (data != null) {
                String phone = data.getPhoneNum();
                String province =data.getProvince();
                String city =data.getCity();
                String country = data.getCounty();
                List<PersonDetailsBean.DataBean.LablesBean> label = data.getLables();
                String labelName = label.get(0).getLabelName();
                tvCategory.setText("" + labelName);
                tvPhone.setText(StringUtils.isEmpty(phone) ? "" : phone);
                tvLocation.setText(province + " " + city + " " + country);
                String sex = data.getSex();
                if (sex.equals("1")) {
                    tvSex.setText("男");
                } else {
                    tvSex.setText("女");
                }
            }
        }
    }
}
