package com.yyq58.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.EditQiuDanDetailsActivity;
import com.yyq58.activity.adapter.QiuDanFragmentAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.QiuDanFragmentListbean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyDialog;

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
                QiuDanFragmentListbean.DataBean bean = mList.get(position);
                //编辑
                Intent intent = new Intent(getActivity(), EditQiuDanDetailsActivity.class);
                if (bean != null) {
                    String typeName = bean.getTypeName();
                    String time = bean.getTime();
                    String province =bean.getProvince();
                    String city =bean.getCity();
                    String county = bean.getCounty();
                    String price = bean.getPrice();
                    String content = bean.getContent();
                    String noticeId = bean.getNoticeId();
                    String labelId = bean.getLabelId();
                    String mianyi = bean.getMianyi();
                    intent.putExtra("typeName",typeName );
                    intent.putExtra("time",time );
                    intent.putExtra("province",province );
                    intent.putExtra("city",city );
                    intent.putExtra("county",county );
                    intent.putExtra("price",price );
                    intent.putExtra("content",content );
                    intent.putExtra("noticeId",noticeId );
                    intent.putExtra("labelId",labelId );
                    intent.putExtra("mianyi",mianyi );
                    startActivity(intent);
                    }
            }

            @Override
            public void onDeleClick(View view, int position) {
                //删除
                showDeleteNoticeDialog(position);
            }

            @Override
            public void onSaveClick(View view, int position) {

            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                queryQiudanList(MyApplication.userId, page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    /***
     * 删除通告
     * @param position
     */
    private void showDeleteNoticeDialog(int position) {
        QiuDanFragmentListbean.DataBean bean = mList.get(position);
        final String noticeId = bean.getNoticeId();
        final MyDialog dialog = new MyDialog(getActivity());
        dialog.setTitle("删除提示");
        dialog.setMessage("是否确认删除");
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteQiiudanNotice(noticeId);
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /***
     * 删除求单通告
     * @param noticeId
     */
    private void deleteQiiudanNotice(String noticeId) {
        startIOSDialogLoading(getActivity(), "");
        Map<String, String> params = new HashMap<>();
        params.put("id", noticeId);
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.DELETE_QIUDAN_URL, params, ConfigUtil.DELETE_QIUDAN_URL_ACTION);
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
        startIOSDialogLoading(getActivity(), "");
        httpPostRequest(ConfigUtil.QUERY_NOTICE_LIST_URL, params, ConfigUtil.QUERY_NOTICE_LIST_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_NOTICE_LIST_URL_ACTION:
                handleQueryQiuDanList(json);
                break;
            case ConfigUtil.DELETE_QIUDAN_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
                    queryQiudanList(MyApplication.userId,page);
                }
                break;
        }
    }

    /***
     * 求单列表处理
     * @param json
     */
    private void handleQueryQiuDanList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        QiuDanFragmentListbean bean = JSON.parseObject(json, QiuDanFragmentListbean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                adapter.setData(mList);
            }
        }
    }
}
