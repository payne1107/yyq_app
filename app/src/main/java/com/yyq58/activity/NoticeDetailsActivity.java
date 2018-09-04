package com.yyq58.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yyq58.R;
import com.yyq58.activity.adapter.RecommendNoticeAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.NoticeDetailsBean;
import com.yyq58.activity.bean.RecommendNoticeBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IRecycleViewOnItemClickListener;
import com.yyq58.activity.widget.MyDialog;
import com.yyq58.activity.widget.MyListView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * 通告详情
 */
public class NoticeDetailsActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView ivAvatar;
    private TextView tvUserName;
    private TextView tvDate;
    private TextView tvContent;
    private TextView tvConent2;
    private TextView tvCategory;
    private TextView tvPrice;
    private TextView tvLocation;
    private TextView tvEndDate;
    private Context mContext;
    private MyListView listView;
    private List<RecommendNoticeBean.DataBean> mList = new ArrayList<>();
    private RecommendNoticeAdapter adapter;
    private AutoLinearLayout layoutQiangDan;
    private String noticeId;
    private Dialog dialog;
    private String content;
    private TextView tvQiangdan;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notice_details);
        mContext = NoticeDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        String noticeTitle =getIntent().getStringExtra("noticeTitle");
         noticeId = getIntent().getStringExtra("noticeid");
        boolean isDeadtime = getIntent().getBooleanExtra("isDeadtime", false);
        setInVisibleTitleIcon("" + noticeTitle, true, true);
        tvSet.setText("分享");
        tvSet.setTextColor(getResources().getColor(R.color.white));
        tvSet.setVisibility(View.VISIBLE);

        ivAvatar = findViewById(R.id.iv_avatar);
        tvUserName = findViewById(R.id.tv_username);
        tvDate = findViewById(R.id.tv_date);
        tvContent = findViewById(R.id.tv_content);
        tvConent2 = findViewById(R.id.tv_content2);
        tvCategory = findViewById(R.id.tv_category);
        tvPrice = findViewById(R.id.tv_price);
        tvLocation = findViewById(R.id.tv_location);
        tvEndDate = findViewById(R.id.tv_end_date);
        listView = findViewById(R.id.listView);
        layoutQiangDan = findViewById(R.id.layout_qiangdan);
        TextView tvQiangdan = findViewById(R.id.tv_qiangdan);

        if (isDeadtime) {
            layoutQiangDan.setBackgroundColor(getResources().getColor(R.color.google_yellow));
            tvQiangdan.setText("通告已过期");
            tvQiangdan.setTextColor(getResources().getColor(R.color.google_red));
            layoutQiangDan.setEnabled(false);
        } else {
            layoutQiangDan.setBackgroundColor(getResources().getColor(R.color.color_4b3a75));
            layoutQiangDan.setEnabled(true);
        }

        adapter = new RecommendNoticeAdapter(mContext, mList);
        listView.setAdapter(adapter);
        queryNoticeDetails(noticeId, MyApplication.userId);
        queryRecommendNoticeList(MyApplication.userId, 0, 1);

        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        layoutQiangDan.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, NoticeDetailsActivity2.class);
                RecommendNoticeBean.DataBean bean = (RecommendNoticeBean.DataBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String title = bean.getTitle();
                    String noticeid = bean.getNoticeId();
                    intent.putExtra("noticeTitle", title);
                    intent.putExtra("noticeid", noticeid);
                    startActivity(intent);
                }
            }
        });

        /***
         * 私聊
         */
        adapter.setOnItemClickListener(new IRecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到聊天页面
                toastMessage("聊天页面");
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


    /****
     * 查询通告详情
     * @param NOTICE_ID  通告id
     * @param CONSUMER_ID 用户id
     */
    private void queryNoticeDetails(String NOTICE_ID,String CONSUMER_ID) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("NOTICE_ID", NOTICE_ID);
        params.put("CONSUMER_ID", CONSUMER_ID);
        httpPostRequest(ConfigUtil.QUERY_NOTICE_DETAILS_URL, params, ConfigUtil.QUERY_NOTICE_DETAILS_URL_ACTION);
    }

    /****
     * 发起抢单请求
     * @param consumer_id  用户id
     * @param notice_id 通告id
     * @param money 金额
     */
    private void qiangDanPost(String consumer_id, String notice_id, String money) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumer_id", consumer_id);
        params.put("notice_id", notice_id);
        params.put("money", money);
        httpPostRequest(ConfigUtil.SAVE_QIANG_DAN_URL, params, ConfigUtil.SAVE_QIANG_DAN_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_NOTICE_DETAILS_URL_ACTION:
                handleQueryNoticeDetails(json);
                break;
            case ConfigUtil.QUERY_RECOMMEND_NOTICE_LIST_URL_ACTION:
                handleQueryRecommendNoticeList(json);
                break;
            case ConfigUtil.SAVE_QIANG_DAN_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    Toast.makeText(mContext, "抢单成功", Toast.LENGTH_LONG).show();
                }
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

    private void handleQueryNoticeDetails(String json) {
        NoticeDetailsBean bean = JSON.parseObject(json, NoticeDetailsBean.class);
        if (bean != null) {
            NoticeDetailsBean.NoticeBean noticeBean = bean.getNotice();
            if (noticeBean != null) {
                content = noticeBean.getContent();
                String avatarUrl = noticeBean.getAVATAR();
                String userName = noticeBean.getAccount();
                String endTime = noticeBean.getTime();
                String createTime =noticeBean.getCreateTime();
                String category = noticeBean.getTYPENAME();
                String price = noticeBean.getPrice();
                String location = noticeBean.getPlace();

                tvConent2.setText(StringUtils.isEmpty(content) ? "" : content);
                tvContent.setText(StringUtils.isEmpty(content) ? "" : content);
                if (!StringUtils.isEmpty(avatarUrl)) {
                    MyApplication.imageLoader.displayImage(avatarUrl, ivAvatar);
                }
                tvUserName.setText(StringUtils.isEmpty(userName) ? "" : userName);
                tvDate.setText(StringUtils.isEmpty(createTime)?"":createTime);
                tvEndDate.setText(StringUtils.isEmpty(endTime) ? "" : endTime);
                tvCategory.setText(StringUtils.isEmpty(category) ? "" : category);
                tvLocation.setText(StringUtils.isEmpty(location) ? "" : location);
                tvPrice.setText(StringUtils.isEmpty(price) ? "" : price);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                //分享
                showShareWindowDialog();
                break;
            case R.id.layout_qiangdan:
                //抢单
                showQiangdanDialog();
                break;
            case R.id.tv_share_wx_cirle:
                shareAction(SHARE_MEDIA.WEIXIN_CIRCLE,mContext,ConfigUtil.INVTITE_SHARE_URL,noticeId,content);
                dialog.dismiss();
                break;
            case R.id.tv_share_wx_friend:
                shareAction(SHARE_MEDIA.WEIXIN, mContext, ConfigUtil.INVTITE_SHARE_URL, noticeId, content);
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                dialog.dismiss();

                break;
        }
    }

    /****
     * 抢单对话框
     */
    private void showQiangdanDialog() {
        final MyDialog dialog = new MyDialog(mContext);
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
                    Toast.makeText(mContext,"抢单金额不能为空",Toast.LENGTH_LONG).show();
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

    /****
     * 展示对话框
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
