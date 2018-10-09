package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.AnnunciateOrderAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.AnnuciteFragmentBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通告订单处理
public class AnnunciateOrderActivity extends BaseActivity {

    private Context mContext;
    private List<AnnuciteFragmentBean.DataBean> mList = new ArrayList<>();
    private PullToRefreshListView listView;
    private int page = 1;
    private boolean swipeLoadMore = false;
    private AnnunciateOrderAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_annunciate_order);
        mContext = AnnunciateOrderActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("通告订单处理", true, true);

        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new AnnunciateOrderAdapter(mContext, mList);
        listView.setAdapter(adapter);
        setListener();
        queryNoticeList(MyApplication.userId, page);
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
                    Intent intent = new Intent(mContext, AnnunciateDetailsActivity.class);
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
                    Intent intent = new Intent(mContext, NoticeEditActivity.class);
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
            }
        });
    }

    /***
     * 删除通告
     * @param position
     */
    private void showDeleteNoticeDialog(int position) {
        AnnuciteFragmentBean.DataBean bean = mList.get(position);
        final String noticeId = bean.getNoticeId();
        final MyDialog dialog = new MyDialog(mContext);
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
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("NOTICE_ID", noticeId);
        httpPostRequest(ConfigUtil.DELETE_NOTICE_URL, params, ConfigUtil.DELETE_NOTICE_URL_ACTION);
    }

    /****
     * 通告列表
     * @param consumerId 用户id
     * @param page
     */
    private void queryNoticeList(String consumerId, int page) {
        startIOSDialogLoading(mContext, "");
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
                    Toast.makeText(mContext, "删除通告成功", Toast.LENGTH_LONG).show();
                    queryNoticeList(MyApplication.userId, page);
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
            } else {
                setEmptyView(listView);
            }
        }
    }
}
