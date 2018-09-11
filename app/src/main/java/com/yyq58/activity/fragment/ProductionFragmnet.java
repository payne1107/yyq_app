package com.yyq58.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.PersonCenterActivity;
import com.yyq58.activity.adapter.DynamicFragmentAdapter;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.DynamicFragmentBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 作品
 */
public class ProductionFragmnet extends BaseFragment {
    private String userId = "";
    private int page = 1;
    private PullToRefreshListView listView;
    private List<DynamicFragmentBean.DataBean> mList = new ArrayList<>();
    private DynamicFragmentAdapter adapter;
    private View mRootView;
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
    public void onAttach(Activity context) {
        super.onAttach(context);
        userId  = ((PersonCenterActivity) context).getUserId();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        listView = mRootView.findViewById(R.id.listView);
        adapter = new DynamicFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
    }

    /***
     * 查询动态
     * @param  flag 不传查动态
     *              传1查作品
     */
    private void queryDynamicList(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", userId);
        params.put("page", String.valueOf(page));
        params.put("flag", "1");
        httpPostRequest(ConfigUtil.QUERY_MINE_DAYNIC_URL, params, ConfigUtil.QUERY_MINE_DAYNIC_URL_ACTION);
    }

    @Override
    public void onResume() {
        super.onResume();
        queryDynamicList(page);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MINE_DAYNIC_URL_ACTION:
                handleQueryMineDaynicList(json);
                break;
        }
    }

    private void handleQueryMineDaynicList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        DynamicFragmentBean bean = JSON.parseObject(json, DynamicFragmentBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                adapter.setData(mList);
            }
        }
    }
}
