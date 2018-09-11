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
import com.yyq58.activity.adapter.MineContactsAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.MineAttentionBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 我的联系人
 */
public class MineContactsActivity extends BaseActivity{

    private PullToRefreshListView listView;
    private List<MineAttentionBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private MineContactsAdapter adapter;
    private int page =1;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_contacts);
        mContext = MineContactsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的联系人", true, true);
        listView = findViewById(R.id.listView);
        adapter = new MineContactsAdapter(mContext,mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                queryMineAttentionList(page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MineAttentionBean.DataBean bean = (MineAttentionBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String toConsumerId = bean.getConsumerId();
                    startActivity(new Intent(mContext, PersonCenterActivity.class).putExtra("userId", toConsumerId));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryMineAttentionList(page);
    }

    /***
     * 查询我关注的人
     * @param page
     */
    private void queryMineAttentionList(int page) {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_MINE_ATTENTION_URL, params, ConfigUtil.QUERY_MINE_ATTENTION_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MINE_ATTENTION_URL_ACTION:
                handleQueryMineAttention(json);
                break;
        }
    }

    /**
     * 处理我关注的人
     * @param json
     */
    private void handleQueryMineAttention(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MineAttentionBean bean = JSON.parseObject(json, MineAttentionBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                adapter.setData(mList);
            }
        }
    }
}
