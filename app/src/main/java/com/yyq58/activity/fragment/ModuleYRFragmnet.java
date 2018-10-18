package com.yyq58.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yyq58.R;
import com.yyq58.activity.adapter.SearchYRListAdapter;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.SearchYRListBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleYRFragmnet extends BaseFragment {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private List<SearchYRListBean.DataBean> mList = new ArrayList<>();
    private SearchYRListAdapter mAdapter;
    private View mRootView;
    private int pageNo=1;
    private RefreshLayout refreshLayout;
    private boolean swipeLoadMore = false;
    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_yr_list, null);
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
        mRecyclerView = mRootView.findViewById(R.id.recyclerview);
        //设置布局管理器为2列，纵向
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new SearchYRListAdapter(getActivity(), mList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));

        queryYRList(pageNo);

        setListener();
    }

    private void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNo = 1;
                queryYRList(pageNo);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNo++;
                swipeLoadMore = true;
                queryYRList(pageNo);
            }
        });
    }

    private void queryYRList(int page) {
        startIOSDialogLoading(getActivity(), "");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("type", "1");
        params.put("caiyi_type", "2");
        httpPostRequest(ConfigUtil.QUERY_YR_LIST_URL, params, ConfigUtil.QUERY_YR_LIST_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_YR_LIST_URL_ACTION:
                handleQueryYRList(json);
                break;
        }
    }

    private void handleQueryYRList(String json) {
        if (swipeLoadMore) {
            refreshLayout.finishLoadMore();
        } else {
            refreshLayout.finishRefresh();
        }
        SearchYRListBean bean = JSON.parseObject(json, SearchYRListBean.class);
        if (bean != null) {
            if (swipeLoadMore) {
                swipeLoadMore = false;
                mList.addAll(mList.size(), bean.getData());
                mAdapter.setData(mList);
            } else {
                mList = bean.getData();
                if (mList != null && mList.size() > 0) {
                    mAdapter.setData(mList);
                }
            }
        }
    }
}
