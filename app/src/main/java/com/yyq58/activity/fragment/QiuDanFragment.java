package com.yyq58.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.QiuDanFragmentAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.QiuDanFragmentListbean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QiuDanFragment extends BaseFragment {

    private PullToRefreshListView listView;
    private List<QiuDanFragmentListbean.DataBean> mList = new ArrayList<>();
    private QiuDanFragmentAdapter adapter;
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
        setLisener();
    }

    private void setLisener() {
        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {
                //编辑
                Toast.makeText(getActivity(), "编辑", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleClick(View view, int position) {
                //删除
                Toast.makeText(getActivity(), "删除", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSaveClick(View view, int position) {

            }
        });
    }

    private void initView() {
        listView = mRootView.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        adapter = new QiuDanFragmentAdapter(getActivity(), mList);
        listView.setAdapter(adapter);

        queryQiudanList(MyApplication.userId,page);
    }


    /***
     * 查询求单列表
     * @param consumerId
     * @param page
     */
    private void queryQiudanList(String consumerId,int page) {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", consumerId);
        params.put("page", String.valueOf(page));
        params.put("isQiudan", "1");
        httpPostRequest(ConfigUtil.QUERY_NOTICE_LIST_URL, params, ConfigUtil.QUERY_NOTICE_LIST_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_NOTICE_LIST_URL_ACTION:
                handleQueryQiuDanList(json);
                break;
        }
    }

    /***
     * 求单列表处理
     * @param json
     */
    private void handleQueryQiuDanList(String json) {
        QiuDanFragmentListbean bean = JSON.parseObject(json, QiuDanFragmentListbean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
