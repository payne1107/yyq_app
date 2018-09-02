package com.yyq58.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.NoticeDetailsActivity;
import com.yyq58.activity.adapter.NewestFragmentAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.Appv1NoticeBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.IRecycleViewOnItemClickListener;
import com.yyq58.activity.widget.MyDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 首页通告-最新
 */
public class NewestFragNment extends BaseFragment {
    private View mRootView;
    private PullToRefreshListView listView;
    private List<Appv1NoticeBean.DataBean> mList = new ArrayList<>();
    private int page = 1;
    private NewestFragmentAdapter adapter;
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
    }

    private void initView() {
        listView = mRootView.findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new NewestFragmentAdapter(getActivity(), mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                queryNoticeList(page,0,"","","","","");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++page;
                swipeLoadMore = true;
                queryNoticeList(page,0,"","","","","");
            }
        });

        //抢单点击事件
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Appv1NoticeBean.DataBean bean = mList.get(position);
                final String noticeId = bean.getNoticeId();
                final MyDialog dialog = new MyDialog(getActivity());
                dialog.setContentView(R.layout.dialog_qiangdan_layout);
                dialog.show();
                TextView tvConfirm = dialog.findViewById(R.id.positiveButton);
                TextView tvCancel = dialog.findViewById(R.id.negativeButton);
                final EditText etMoney = dialog.findViewById(R.id.message);
                tvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //确定
                        String money = etMoney.getText().toString();
                        if (StringUtils.isEmpty(money)) {
                            Toast.makeText(getActivity(),"抢单金额不能为空",Toast.LENGTH_LONG).show();
                            return;
                        }
                        qiangDanPost(MyApplication.userId, noticeId, money);
                        dialog.dismiss();
                    }
                });

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //取消
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });

        /****
         * listviewItem点击事件
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), NoticeDetailsActivity.class);
                Appv1NoticeBean.DataBean bean = (Appv1NoticeBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String title = bean.getTitle();
                    String noticeid = bean.getNoticeId();
                    boolean isDeadtime = bean.isDeadTime();
                    intent.putExtra("isDeadtime", isDeadtime);
                    intent.putExtra("noticeTitle", title);
                    intent.putExtra("noticeid", noticeid);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        queryNoticeList(page, 0, "", "", "", "", "");
    }

    /****
     * 发起抢单请求
     * @param consumer_id  用户id
     * @param notice_id 通告id
     * @param money 金额
     */
    private void qiangDanPost(String consumer_id, String notice_id, String money) {
        startIOSDialogLoading(getActivity(), "");
        Map<String, String> params = new HashMap<>();
        params.put("consumer_id", consumer_id);
        params.put("notice_id", notice_id);
        params.put("money", money);
        httpPostRequest(ConfigUtil.SAVE_QIANG_DAN_URL, params, ConfigUtil.SAVE_QIANG_DAN_URL_ACTION);
    }


    /****
     * 查询通告列表数据
     * @param page 分页
     * @param type 1:模特 2：主持 3：舞蹈 4：主播 0：全部
     * @param keywords  对标题和内容
     * @param province 省
     * @param city 市
     * @param county 区
     * @param labelId 标签
     */
    private void queryNoticeList(int page,int type,String keywords,String province,String city,String county,String labelId) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("TYPE", String.valueOf(type));
        if (!StringUtils.isEmpty(keywords)) {
            params.put("keywords", keywords);
        }
        if (!StringUtils.isEmpty(province)) {
            params.put("province", province);
        }
        if (!StringUtils.isEmpty(city)) {
            params.put("city", city);
        }
        if (!StringUtils.isEmpty(county)) {
            params.put("county", county);
        }
        if (!StringUtils.isEmpty(labelId)) {
            params.put("labelId", labelId);
        }
        startIOSDialogLoading(getActivity(),"");
        httpPostRequest(ConfigUtil.QUERY_APPV1_NOTICE_LIST_URL, params, ConfigUtil.QUERY_APPV1_NOTICE_LIST_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_APPV1_NOTICE_LIST_URL_ACTION:
                handleQueryAppv1NoticeList(json);
                break;
            case ConfigUtil.SAVE_QIANG_DAN_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    Toast.makeText(getActivity(), "抢单成功", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void handleQueryAppv1NoticeList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        Appv1NoticeBean bean = JSON.parseObject(json, Appv1NoticeBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), bean.getData());
                    adapter.setData(mList);
                } else {
                    mList = bean.getData();
                    adapter.setData(mList);
                }
            } else {
                setEmptyView(listView);
            }
        }
    }
}
