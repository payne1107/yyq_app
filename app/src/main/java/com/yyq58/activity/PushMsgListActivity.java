package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.PushMsgAdapter;
import com.yyq58.activity.adapter.SystemNoticeAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.PushMsgListBean;
import com.yyq58.activity.bean.SystemNoticeBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushMsgListActivity extends BaseActivity{
    private int pageNo = 1;
    private PullToRefreshListView listView;
    private List<PushMsgListBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private PushMsgAdapter adapter;
    private boolean swipeLoadMore = false;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_push_msg_list);
        mContext = PushMsgListActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("消息助手", true, true);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new PushMsgAdapter(mContext, mList);
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
        httpPostRequest(ConfigUtil.QUERY_PUSH_MSG_LIST_URL, params, ConfigUtil.QUERY_PUSH_MSG_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PUSH_MSG_LIST_URL_ACTION:
                handleQuerySysNoticeList(json);
                break;
        }
    }

    private void handleQuerySysNoticeList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        PushMsgListBean bean = JSON.parseObject(json, PushMsgListBean.class);
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
