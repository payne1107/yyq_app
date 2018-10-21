package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.SystemNoticeAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.SystemNoticeBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemNoticeActivity extends BaseActivity{
    private int pageNo = 1;
    private PullToRefreshListView listView;
    private List<SystemNoticeBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private SystemNoticeAdapter adapter;
    private boolean swipeLoadMore = false;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_system_notice);
        mContext = SystemNoticeActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("系统通知", true, true);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new SystemNoticeAdapter(mContext, mList);
        listView.setAdapter(adapter);

        querySysNoticeList(pageNo);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                querySysNoticeList(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo++;
                swipeLoadMore = true;
                querySysNoticeList(pageNo);
            }
        });
    }

    private void querySysNoticeList(int page) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.QUERY_SYSTEM_NOITCE_LIST_URL, params, ConfigUtil.QUERY_SYSTEM_NOITCE_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_SYSTEM_NOITCE_LIST_URL_ACTION:
                handleQuerySysNoticeList(json);
                break;
        }
    }

    private void handleQuerySysNoticeList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        SystemNoticeBean bean = JSON.parseObject(json, SystemNoticeBean.class);
        if (bean != null) {
            if (swipeLoadMore) {
                swipeLoadMore = false;
                mList.addAll(mList.size(), bean.getData());
                adapter.setData(mList);
            } else {
                mList = bean.getData();
                if (mList != null && mList.size() > 0) {
                    adapter.setData(mList);
                }
            }
        }
    }
}
