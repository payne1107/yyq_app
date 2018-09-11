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
import com.yyq58.activity.adapter.MineAttentionAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.MineAttentionBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*我关注的人*/
public class MineAttentionActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private Context mContext;
    private List<MineAttentionBean.DataBean> mList = new ArrayList<>();
    private int page = 1;
    private MineAttentionAdapter adapter;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_attention);
        mContext = MineAttentionActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的关注", true, true);
        listView = findViewById(R.id.listView);
        adapter = new MineAttentionAdapter(mContext,mList);
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

        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {

            }

            @Override
            public void onDeleClick(View view, int position) {
                //取消关注
                MineAttentionBean.DataBean bean = mList.get(position);
                if (bean != null) {
                    String attentionId = bean.getConsumerId();
                    cancelAttentionYR(attentionId);
                }
            }

            @Override
            public void onSaveClick(View view, int position) {

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

    /***
     * 取消关注艺人
     * @param attentionId  关注人id
     */
    private void cancelAttentionYR(String attentionId) {
        startIOSDialogLoading(mContext,"取消关注");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("toConsumerId", attentionId);
        httpPostRequest(ConfigUtil.CANCEL_ATTENTION_URL, params, ConfigUtil.CANCEL_ATTENTION_URL_ACTION);
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
            case ConfigUtil.CANCEL_ATTENTION_URL_ACTION:
                //取消关注艺人
                if (getRequestCode(json) == 1000) {
                    toastMessage("取消关注");
                    queryMineAttentionList(page);
                }
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
