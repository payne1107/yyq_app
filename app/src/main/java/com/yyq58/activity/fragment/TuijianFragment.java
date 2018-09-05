package com.yyq58.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.AnnunciateDetailsActivity;
import com.yyq58.activity.adapter.TuijianFragmentAdapter;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.TuijianFragmentListBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 推荐艺人列表
 */
public class TuijianFragment extends BaseFragment{

    private MyListView listView;
    private List<TuijianFragmentListBean.DataBean> mList = new ArrayList<>();
    private TuijianFragmentAdapter adapter;
    private String province;
    private String city;
    private int type;
    private int page = 1;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_tuijian_list, null);
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
        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {
                //私聊事件
                Toast.makeText(getActivity(), "siliao", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleClick(View view, int position) {

            }

            @Override
            public void onSaveClick(View view, int position) {

            }
        });
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        province   = ((AnnunciateDetailsActivity) context).getProvince();
        city  = ((AnnunciateDetailsActivity) context).getCity();
        type  = ((AnnunciateDetailsActivity) context).getType();
    }

    private void initView() {
        listView = mRootView.findViewById(R.id.listview);
        adapter = new TuijianFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);

        queryTuijianList(type,page,province,city);
    }

    /***
     * 获取推荐艺人列表
     * @param type
     * @param page
     */
    private void queryTuijianList(int type,int page,String province,String city) {
        Map<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(type));
        params.put("page", String.valueOf(page));
        params.put("province", province);
        params.put("city", city);
        httpPostRequest(ConfigUtil.QUERY_TUIJIAN_LISTS_URL, params, ConfigUtil.QUERY_TUIJIAN_LISTS_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_TUIJIAN_LISTS_URL_ACTION:
                handleQueryTuijianLists(json);
                break;
        }
    }

    /***
     * 推荐艺人列表
     * @param json
     */
    private void handleQueryTuijianLists(String json) {
        TuijianFragmentListBean bean = JSON.parseObject(json, TuijianFragmentListBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
