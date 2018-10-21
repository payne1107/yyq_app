package com.yyq58.activity.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.MineContactsActivity;
import com.yyq58.activity.PushMsgListActivity;
import com.yyq58.activity.SystemNoticeActivity;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.UnreadMsgBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

public class Fragment3 extends BaseFragment implements View.OnClickListener {

    private TextView tvSet;
    private AutoLinearLayout layoutSysNotice;
    private AutoLinearLayout layoutPushMsg;
    private TextView tvUnreadMsg;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment3, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        layoutSysNotice.setOnClickListener(this);
        layoutPushMsg.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        setInVisibleTitleIcon("消息列表", true, false);
        tvSet = mRootView.findViewById(R.id.activity_set);
        tvSet.setVisibility(View.VISIBLE);
        Drawable drawable1 = getResources().getDrawable(R.mipmap.tabbar_usercenter);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        tvSet.setCompoundDrawables(null,drawable1 ,null,null);
        layoutSysNotice = mRootView.findViewById(R.id.layout_system_notice);
        layoutPushMsg = mRootView.findViewById(R.id.layout_push_msg);
        tvUnreadMsg = mRootView.findViewById(R.id.tv_unread_msg);

        queryUnreadMsg();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                startActivity(new Intent(getActivity(), MineContactsActivity.class));
                break;
            case R.id.layout_system_notice:
                startActivity(new Intent(getActivity(), SystemNoticeActivity.class));
                break;
            case R.id.layout_push_msg:
                startActivity(new Intent(getActivity(), PushMsgListActivity.class));
                break;
        }
    }

    private void queryUnreadMsg() {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.QUERY_SYSTEM_UNREAD_MSG_URL, params, ConfigUtil.QUERY_SYSTEM_UNREAD_MSG_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_SYSTEM_UNREAD_MSG_URL_ACTION:
                handleQuerySysUnreadCount(json);
                break;
        }
    }

    private void handleQuerySysUnreadCount(String json) {
        UnreadMsgBean bean = JSON.parseObject(json, UnreadMsgBean.class);
        if (bean != null) {
            UnreadMsgBean.DataBean data = bean.getData();
            if (data != null) {
                int unreadCount = data.getUnreadNums();
                if (unreadCount <= 0) {
                    tvUnreadMsg.setVisibility(View.GONE);
                } else {
                    tvUnreadMsg.setText("" + unreadCount);
                    tvUnreadMsg.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
