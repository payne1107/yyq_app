package com.yyq58.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.AnnunciateDetailsActivity;
import com.yyq58.activity.adapter.QiangdanFragmentAdapter;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.QiangDanFragmentBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 抢单艺人列表
 */
public class QiangDanYRFragment extends BaseFragment{

    private MyListView listview;
    private List<QiangDanFragmentBean.DataBean> mList = new ArrayList<>();
    private QiangdanFragmentAdapter adapter;
    private String noticeId;
    private int page = 1;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_qiangdan_list, null);
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        noticeId  = ((AnnunciateDetailsActivity) activity).getNoticeId();
    }

    private void setListener() {
        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {
                Toast.makeText(getActivity(),"siliao",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleClick(View view, int position) {

            }

            @Override
            public void onSaveClick(View view, int position) {
                Toast.makeText(getActivity(),"zhifu",Toast.LENGTH_LONG).show();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "跳转到个人中心", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        listview = mRootView.findViewById(R.id.listview);
        adapter = new QiangdanFragmentAdapter(getActivity(),mList);
        listview.setAdapter(adapter);
        queryQiangdanList(noticeId,page);
    }

    /***
     * 获取抢单艺人列表
     * @param noticeId 通告id
     * @param page
     */
    private void queryQiangdanList(String noticeId,int page) {
        Map<String, String> params = new HashMap<>();
        params.put("noticeId", noticeId);
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QIANG_DAN_LISTS_BY_NOTICEID_URL, params, ConfigUtil.QIANG_DAN_LISTS_BY_NOTICEID_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QIANG_DAN_LISTS_BY_NOTICEID_URL_ACTION:
                handleQueryQiangdanLists(json);
                break;
        }
    }

    /***
     * 抢单艺人列表
     * @param json
     */
    private void handleQueryQiangdanLists(String json) {
        QiangDanFragmentBean bean = JSON.parseObject(json, QiangDanFragmentBean.class);
        if (bean != null) {
             mList = bean.getData();
            adapter.setData(mList);
        }
    }
}
