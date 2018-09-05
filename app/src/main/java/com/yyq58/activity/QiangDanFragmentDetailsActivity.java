package com.yyq58.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yyq58.R;
import com.yyq58.activity.adapter.QiangdanFragmentDetailsAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.RecommendNoticeBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IRecycleViewOnItemClickListener;
import com.yyq58.activity.widget.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * 抢单详情
 */
public class QiangDanFragmentDetailsActivity extends BaseActivity implements View.OnClickListener {
    private String noticeId;
    private String title;
    private MyListView listView;
    private Context mContext;
    private List<RecommendNoticeBean.DataBean> mList = new ArrayList<>();
    private QiangdanFragmentDetailsAdapter adapter;
    private int page = 1;
    private Dialog dialog;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_qiangdan_details);
        mContext = QiangDanFragmentDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        title = getIntent().getStringExtra("title");
        setInVisibleTitleIcon("" + title, true, true);

        tvSet.setVisibility(View.VISIBLE);
        tvSet.setText("分享");
        tvSet.setTextColor(getResources().getColor(R.color.white));

        noticeId = getIntent().getStringExtra("noticeId");
        String avatarUrl =getIntent().getStringExtra("avatarUrl");
        String accountName =getIntent().getStringExtra("accountName");
        String createTime =getIntent().getStringExtra("createTime");

        String content =getIntent().getStringExtra("content");
        String typename =getIntent().getStringExtra("typename");
        String price =getIntent().getStringExtra("price");
        String time = getIntent().getStringExtra("time");
        String location = getIntent().getStringExtra("location");
        int type = getIntent().getIntExtra("type", 0);


        CircleImageView circleImageView =findViewById(R.id.iv_avatar);
        TextView tvUsername =findViewById(R.id.tv_username);
        TextView tvDate = findViewById(R.id.tv_date);
        TextView tvTitle =findViewById(R.id.tv_content);
        TextView tvContent =findViewById(R.id.tv_content2);
        TextView categoryType =findViewById(R.id.tv_category);
        TextView tvPrice =findViewById(R.id.tv_price);
        TextView tvLocation =findViewById(R.id.tv_location);
        TextView tvEndTime = findViewById(R.id.tv_end_date);
        listView = findViewById(R.id.listView);
        adapter = new QiangdanFragmentDetailsAdapter(mContext, mList);
        listView.setAdapter(adapter);

        if (!StringUtils.isEmpty(avatarUrl)) {
            MyApplication.imageLoader.displayImage(avatarUrl, circleImageView);
        }
        tvUsername.setText(StringUtils.isEmpty(accountName) ? "" : accountName);
        tvDate.setText(StringUtils.isEmpty(createTime) ? "" : createTime);
        tvTitle.setText(StringUtils.isEmpty(title) ? "" : title);
        tvContent.setText(StringUtils.isEmpty(content) ? "" : content);
        categoryType.setText(StringUtils.isEmpty(typename) ? "" : typename);
        tvPrice.setText(StringUtils.isEmpty(price) ? "" : price);
        tvLocation.setText(StringUtils.isEmpty(location) ? "" : location);
        tvEndTime.setText(StringUtils.isEmpty(time) ? "" : time);

        queryRecommendNoticeList(MyApplication.userId,type,page);


        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toastMessage("私聊");
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    /****
     * 查询推荐通告列表
     * @param consumerId  用户id
     * @param type 类型
     * @param page
     */
    private void queryRecommendNoticeList(String consumerId, int type, int page) {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", consumerId);
        params.put("type", String.valueOf(type));
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_RECOMMEND_NOTICE_LIST_URL, params, ConfigUtil.QUERY_RECOMMEND_NOTICE_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_RECOMMEND_NOTICE_LIST_URL_ACTION:
                handleQueryRecommendNoticeList(json);
                break;
        }
    }

    /****
     * 推荐列表
     * @param json
     */
    private void handleQueryRecommendNoticeList(String json) {
        RecommendNoticeBean bean = JSON.parseObject(json, RecommendNoticeBean.class);
        if (bean != null) {
            mList = bean.getData();
            adapter.setData(mList);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                showShareWindowDialog();
                break;
            case R.id.tv_share_wx_cirle:
                shareAction(SHARE_MEDIA.WEIXIN_CIRCLE,mContext,ConfigUtil.INVTITE_SHARE_URL,noticeId,title);
                dialog.dismiss();
                break;
            case R.id.tv_share_wx_friend:
                shareAction(SHARE_MEDIA.WEIXIN, mContext, ConfigUtil.INVTITE_SHARE_URL, noticeId, title);
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                dialog.dismiss();

                break;
        }
    }

    /****
     * 分享对话框
     */
    private void showShareWindowDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_share_layout, null);
        dialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        /**  不加 下面两句 居中显示 start */
        wl.x = 0;
        wl.y =this.getWindowManager().getDefaultDisplay().getHeight();
        /**  不加 下面两句 居中显示 end*/
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView tvShareWxCirle = (TextView) dialog.findViewById(R.id.tv_share_wx_cirle);
        TextView tvShareWxFriend = (TextView) dialog.findViewById(R.id.tv_share_wx_friend);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        tvShareWxCirle.setOnClickListener(this);
        tvShareWxFriend.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }
}
