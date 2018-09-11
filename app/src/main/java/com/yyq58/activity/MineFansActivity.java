package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.MineFansAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.MineFansListBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*我的粉丝
 * */
public class MineFansActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private List<MineFansListBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private MineFansAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_fans);
        mContext = MineFansActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的粉丝", true, true);
        listView = findViewById(R.id.listView);
        adapter = new MineFansAdapter(mContext,mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                queryFansList(page);
            }
        });

        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {

            }

            @Override
            public void onDeleClick(View view, int position) {
                MineFansListBean.DataBean bean = mList.get(position);
                if (bean != null) {
                    boolean isFollow = bean.isIsFollow();
                    String consumerId =bean.getConsumerId();
                    if (isFollow) {
                        //取消关注
                        cancelAttention(consumerId);
                    } else {
                        //关注
                        attention(consumerId);
                    }
                }
            }

            @Override
            public void onSaveClick(View view, int position) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MineFansListBean.DataBean bean = (MineFansListBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    //用户详情
                    String toConsumerId =bean.getConsumerId();
                    startActivity(new Intent(mContext, PersonCenterActivity.class).putExtra("userId", toConsumerId));
                }
            }
        });
    }

    /***
     * 关注
     * @param toConsumerId
     */
    private void attention(String toConsumerId) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("toConsumerId",toConsumerId);
        httpPostRequest(ConfigUtil.ATTENTION_YR_URL, params, ConfigUtil.ATTENTION_YR_URL_ACTION);
    }

    /***
     * 取消关注
     * @param toConsumerId
     */
    private void cancelAttention(String toConsumerId) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("toConsumerId",toConsumerId);
        httpPostRequest(ConfigUtil.CANCEL_ATTENTION_URL, params, ConfigUtil.CANCEL_ATTENTION_URL_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryFansList(page);
    }


    /***
     * 查询粉丝列表
     */
    private void queryFansList(int page) {
        startIOSDialogLoading(mContext, "加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_FANS_LIST_URL, params, ConfigUtil.QUERY_FANS_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_FANS_LIST_URL_ACTION:
                handleQueryFansList(json);
                break;
            case ConfigUtil.ATTENTION_YR_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("关注成功");
                    queryFansList(page);
                }
                break;
            case ConfigUtil.CANCEL_ATTENTION_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("取消关注");
                    queryFansList(page);
                }
                break;
        }
    }

    private void handleQueryFansList(String json) {
        Log.d("Dong", "--<" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MineFansListBean bean = JSON.parseObject(json, MineFansListBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
