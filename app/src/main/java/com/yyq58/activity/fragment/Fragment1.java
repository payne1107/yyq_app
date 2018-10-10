package com.yyq58.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.IntegralActivity;
import com.yyq58.activity.MineFansActivity;
import com.yyq58.activity.MineFinanceActivity;
import com.yyq58.activity.MineOrderActivity;
import com.yyq58.activity.ScheduleManagementActivity;
import com.yyq58.activity.SearchNoticeActivity;
import com.yyq58.activity.adapter.TalentTypeAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.PersonDetailsBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.SPUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

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
    private EditText etSearch;
    private AutoLinearLayout layoutBalnace;
    private AutoLinearLayout layoutFans;
    private TextView tvJifenNum;
    private TextView tvFansNum;
    private TextView tvOrderNum;
    private TextView tvBalance;
    private AutoLinearLayout layoutOrder;
    private AutoLinearLayout layoutJifen;
    private AutoLinearLayout layoutCalendar;

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
        etSearch = mRootView.findViewById(R.id.et_search);
        layoutBalnace = mRootView.findViewById(R.id.layout_balance);
        layoutFans = mRootView.findViewById(R.id.layout_fans);
        tvJifenNum = mRootView.findViewById(R.id.tv_jifen_num);
        tvFansNum = mRootView.findViewById(R.id.tv_fans_num);
        tvOrderNum = mRootView.findViewById(R.id.tv_order_num);
        tvBalance = mRootView.findViewById(R.id.tv_balance);
        layoutOrder = mRootView.findViewById(R.id.layout_order);
        layoutJifen = mRootView.findViewById(R.id.layout_jifen);
        layoutCalendar = mRootView.findViewById(R.id.layout_calendar);


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
        etSearch.setOnClickListener(this);
        layoutBalnace.setOnClickListener(this);
        layoutFans.setOnClickListener(this);
        layoutOrder.setOnClickListener(this);
        layoutJifen.setOnClickListener(this);
        layoutCalendar.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        queryPersonDetails();
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
            case R.id.et_search:
                startActivity(new Intent(getActivity(), SearchNoticeActivity.class));
                break;
            case R.id.layout_balance:
                //跳转到我的财务
                startActivity(new Intent(getActivity(), MineFinanceActivity.class));
                break;
            case R.id.layout_fans:
                startActivity(new Intent(getActivity(), MineFansActivity.class));
                break;
            case R.id.layout_order:
                startActivity(new Intent(getActivity(), MineOrderActivity.class));
                break;
            case R.id.layout_jifen:
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
            case R.id.layout_calendar:
                startActivity(new Intent(getActivity(), ScheduleManagementActivity.class));
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

    /***
     * 查询个人信息
     */
    private void queryPersonDetails() {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
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
        PersonDetailsBean bean = JSON.parseObject(json, PersonDetailsBean.class);
        if (bean != null) {
            PersonDetailsBean.DataBean data = bean.getData();
            if (data != null) {
                double balance = data.getChanges(); //余额
                String orderNum = data.getOrderNums();//订单
                String fansNum = data.getFansNums();//粉丝
                double jifen = data.getJifen();//积分

                tvBalance.setText("" + balance);
                tvJifenNum.setText("" + jifen);
                tvFansNum.setText(StringUtils.isEmpty(fansNum) ? "" : fansNum);
                tvOrderNum.setText(StringUtils.isEmpty(orderNum) ? "" : orderNum);
            }
        }
    }
}
