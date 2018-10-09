package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.QiangDanListAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.QiangDanFramgmentBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//抢单列表
public class QiangDanActivity extends BaseActivity {
    private PullToRefreshListView listView;
    private List<QiangDanFramgmentBean.DataBean> mList = new ArrayList<>();
    private QiangDanListAdapter adapter;
    private int page = 1;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_qiangdan);
        mContext = QiangDanActivity.this;
    }

    @Override
    protected void initData() {
        queryQiangdanList(page, MyApplication.userId);
    }


    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("抢单定单处理", true, true);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        adapter = new QiangDanListAdapter(mContext,mList);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                queryQiangdanList(page, MyApplication.userId);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QiangDanFramgmentBean.DataBean bean = (QiangDanFramgmentBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String noticeId = bean.getNoticeId();
                    String avatarUrl = bean.getAvatar();
                    String accountName = bean.getAccount();
                    String createTime = bean.getCreateTime();
                    String title = bean.getTitle();
                    String content = bean.getContent();
                    String typename = bean.getTypeName();
                    String price = bean.getPrice();
                    String time = bean.getTime();
                    String location = bean.getPlace();
                    int type = bean.getType();
                    Intent intent = new Intent(mContext, QiangDanFragmentDetailsActivity.class);
                    intent.putExtra("noticeId", noticeId);
                    intent.putExtra("avatarUrl", avatarUrl);
                    intent.putExtra("accountName", accountName);
                    intent.putExtra("createTime", createTime);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    intent.putExtra("typename", typename);
                    intent.putExtra("price", price);
                    intent.putExtra("time", time);
                    intent.putExtra("location", location);
                    intent.putExtra("type", type);

                    startActivity(intent);
                }
            }
        });
    }

    /***
     * 查询抢单列表
     * @param page
     * @param userId
     */
    private void queryQiangdanList(int page,String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", userId);
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_QIANGDAN_LISTS_BY_USERID_URL, params, ConfigUtil.QUERY_QIANGDAN_LISTS_BY_USERID_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_QIANGDAN_LISTS_BY_USERID_URL_ACTION:
                handleQueryQiangdanByUserId(json);
                break;
        }
    }
    /***
     * 处理抢单列表
     * @param json
     */
    private void handleQueryQiangdanByUserId(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        QiangDanFramgmentBean bean = JSON.parseObject(json, QiangDanFramgmentBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
