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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.AnnunciateDetailsActivity;
import com.yyq58.activity.NoticeEditActivity;
import com.yyq58.activity.adapter.AnnunciateFrgmentAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.AnnuciteFragmentBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnunciateFragNment extends BaseFragment {

    private PullToRefreshListView listView;
    private List<AnnuciteFragmentBean.DataBean> mList = new ArrayList<>();
    private AnnunciateFrgmentAdapter adapter;
    private int page = 1;
    private boolean swipeLoadMore = false;

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
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                queryNoticeList(MyApplication.userId, page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++page;
                swipeLoadMore = true;
                queryNoticeList(MyApplication.userId, page);
            }
        });

        /****
         * listviewItem点击事件
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AnnuciteFragmentBean.DataBean bean = (AnnuciteFragmentBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String noticeId = bean.getNoticeId();
                    String avatarUrl = bean.getAvatar();
                    String accountName = bean.getAccount();
                    String createTime = bean.getCreateTime();
                    String title =bean.getTitle();
                    String content = bean.getContent();
                    String typename = bean.getTypeName();
                    String price = bean.getPrice();
                    String time = bean.getTime();
                    String location = bean.getPlace();
                    String province =bean.getProvince();
                    String city = bean.getCity();
                    int type = bean.getType();
                    Intent intent = new Intent(getActivity(), AnnunciateDetailsActivity.class);
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
                    intent.putExtra("province", province);
                    intent.putExtra("city", city);
                    intent.putExtra("type", type);

                    startActivity(intent);
                }
            }
        });

        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {
                //编辑事件
                AnnuciteFragmentBean.DataBean bean = mList.get(position);
                if (bean != null) {
                    Intent intent = new Intent(getActivity(), NoticeEditActivity.class);
                    String labelName = bean.getLabelName();
                    String time = bean.getTime();
                    String location = bean.getPlace();
                    String detailsPlace = bean.getDetailPlace();
                    String price = bean.getPrice();
                    String content = bean.getContent();
                    int num = bean.getNum();
                    String noticeId = bean.getNoticeId();
                    String manyuan = bean.getManyuan();
                    String province =bean.getProvince();
                    String city =bean.getCity();
                    String county = bean.getCounty();
                    String title = bean.getTypeName();
                    String mianyi = bean.getMianyi();

                    intent.putExtra("labelName",labelName );
                    intent.putExtra("time", time);
                    intent.putExtra("location", location);
                    intent.putExtra("detailsPlace", detailsPlace);
                    intent.putExtra("price", price);
                    intent.putExtra("content", content);
                    intent.putExtra("noticeId", noticeId);
                    intent.putExtra("num", num);
                    intent.putExtra("manyuan", manyuan);
                    intent.putExtra("province", province);
                    intent.putExtra("city", city);
                    intent.putExtra("county", county);
                    intent.putExtra("title", title);
                    intent.putExtra("mianyi", mianyi);
                    startActivity(intent);
                }
            }

            @Override
            public void onDeleClick(View view, int position) {
                //删除事件
                showDeleteNoticeDialog(position);
            }

            @Override
            public void onSaveClick(View view, int position) {
                //设为满员
                setNoticeFull(position);
            }
        });
    }

    /****
     * 设为满员
     * @param position
     */
    private void setNoticeFull(int position) {
        AnnuciteFragmentBean.DataBean bean = mList.get(position);
        final String noticeId = bean.getNoticeId();
        final MyDialog dialog = new MyDialog(getActivity());
        dialog.setTitle("满员提示");
        dialog.setMessage("是否确认设置为满员");
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNoticeFull(noticeId,1);
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
     * 设置为满员请求
     * @param noticeId 通告id
     * @param status 满员状态 1
     */
    private void setNoticeFull(String noticeId, int status) {
        Map<String, String> params = new HashMap<>();
        params.put("noticeId", noticeId);
        params.put("status", String.valueOf(status));
        httpPostRequest(ConfigUtil.UPDATE_NOTICE_MANYUAN_URL, params, ConfigUtil.UPDATE_NOTICE_MANYUAN_URL_ACTION);
    }

    /***
     * 删除通告
     * @param position
     */
    private void showDeleteNoticeDialog(int position) {
        AnnuciteFragmentBean.DataBean bean = mList.get(position);
        final String noticeId = bean.getNoticeId();
        final MyDialog dialog = new MyDialog(getActivity());
        dialog.setTitle("删除提示");
        dialog.setMessage("是否确认删除");
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNotice(noticeId);
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
     * 删除通告
     * @param noticeId
     */
    private void deleteNotice(String noticeId) {
        startIOSDialogLoading(getActivity(), "");
        Map<String, String> params = new HashMap<>();
        params.put("NOTICE_ID", noticeId);
        httpPostRequest(ConfigUtil.DELETE_NOTICE_URL, params, ConfigUtil.DELETE_NOTICE_URL_ACTION);
    }

    /***
     * 初始化view
     */
    private void initView() {
        listView = mRootView.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new AnnunciateFrgmentAdapter(getActivity(), mList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        queryNoticeList(MyApplication.userId, page);
    }

    /****
     * 通告列表
     * @param consumerId 用户id
     * @param page
     */
    private void queryNoticeList(String consumerId, int page) {
        startIOSDialogLoading(getActivity(), "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", consumerId);
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_NOTICE_LIST_URL, params, ConfigUtil.QUERY_NOTICE_LIST_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_NOTICE_LIST_URL_ACTION:
                handleQueryNoticeList(json);
                break;
            case ConfigUtil.DELETE_NOTICE_URL_ACTION:
                //删除通告
                if (getRequestCode(json) == 1000) {
                    Toast.makeText(getActivity(), "删除通告成功", Toast.LENGTH_LONG).show();
                    queryNoticeList(MyApplication.userId, page);
                }
                break;
            case ConfigUtil.UPDATE_NOTICE_MANYUAN_URL_ACTION:
                //满员
                if (getRequestCode(json) == 1000) {
                    Toast.makeText(getActivity(), "设置满员成功", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void handleQueryNoticeList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        Log.d("Dong", "--->" +json);
        AnnuciteFragmentBean bean = JSON.parseObject(json, AnnuciteFragmentBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), bean.getData());
                    adapter.setData(mList);
                } else {
                    adapter.setData(bean.getData());
                }
            }else {
                setEmptyView(listView);
            }
        }
    }
}
