package com.yyq58.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.AnnunciateDetailsActivity;
import com.yyq58.activity.QiangDanFragmentDetailsActivity;
import com.yyq58.activity.adapter.QiangDanListAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.QiangDanFramgmentBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 抢单列表
 */
public class QiangDanFragmnet extends BaseFragment {

    private PullToRefreshListView listView;
    private List<QiangDanFramgmentBean.DataBean> mList = new ArrayList<>();
    private QiangDanListAdapter adapter;
    private int page = 1;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_newest_layout, null);
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
                    Intent intent = new Intent(getActivity(), QiangDanFragmentDetailsActivity.class);
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

    private void initView() {
        listView = mRootView.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        adapter = new QiangDanListAdapter(getActivity(),mList);
        listView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        queryQiangdanList(page, MyApplication.userId);
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
