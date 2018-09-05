package com.yyq58.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.QiangDanListAdapter;
import com.yyq58.activity.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/***
 * 抢单列表
 */
public class QiangDanFragmnet extends BaseFragment {

    private PullToRefreshListView listView;
    private List<String> mList = new ArrayList<>();
    private QiangDanListAdapter adapter;

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

            }
        });
    }

    private void initView() {
        listView = mRootView.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mList.add("测试1");
        mList.add("测试1");
        mList.add("测试1");
        mList.add("测试1");
        adapter = new QiangDanListAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
    }
}
