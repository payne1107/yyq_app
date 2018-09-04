package com.yyq58.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.fragment.QiangdanFragment;
import com.yyq58.activity.fragment.TuijianFragment;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;

/***
 * 历史纪录---》通告详情
 */
public class AnnunciateDetailsActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private Dialog dialog;
    private TextView tvQiangdanYR;
    private TextView tvTuijianYR;
    private FrameLayout frameLayout;
    private Fragment[] mFragments;
    private int mIndex;
    private String noticeId;
    private String title;
    private String province;
    private String city;
    private int type;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_annunciate_details);
        mContext = AnnunciateDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("", true, true);
        tvSet.setVisibility(View.VISIBLE);
        tvSet.setText("分享");
        tvSet.setTextColor(getResources().getColor(R.color.white));

        noticeId = getIntent().getStringExtra("noticeId");
        String avatarUrl =getIntent().getStringExtra("avatarUrl");
        String accountName =getIntent().getStringExtra("accountName");
        String createTime =getIntent().getStringExtra("createTime");
        title = getIntent().getStringExtra("title");
        String content =getIntent().getStringExtra("content");
        String typename =getIntent().getStringExtra("typename");
        String price =getIntent().getStringExtra("price");
        String time = getIntent().getStringExtra("time");
        String location = getIntent().getStringExtra("location");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        type = getIntent().getIntExtra("type", 0);



        CircleImageView circleImageView =findViewById(R.id.iv_avatar);
        TextView tvUsername =findViewById(R.id.tv_username);
        TextView tvDate = findViewById(R.id.tv_date);
        TextView tvTitle =findViewById(R.id.tv_content);
        TextView tvContent =findViewById(R.id.tv_content2);
        TextView categoryType =findViewById(R.id.tv_category);
        TextView tvPrice =findViewById(R.id.tv_price);
        TextView tvLocation =findViewById(R.id.tv_location);
        TextView tvEndTime = findViewById(R.id.tv_end_date);
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

        tvQiangdanYR = findViewById(R.id.tv_qiangdan_yr);
        tvTuijianYR = findViewById(R.id.tv_tuijian_yr);
        frameLayout = findViewById(R.id.framelayout);

        setListener();
        initFragment();
    }


    private void setListener() {
        tvSet.setOnClickListener(this);
        tvQiangdanYR.setOnClickListener(this);
        tvTuijianYR.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                //分享
                showShareWindowDialog();
                break;
            case R.id.tv_share_wx_cirle:
                shareAction(SHARE_MEDIA.WEIXIN_CIRCLE,mContext, ConfigUtil.INVTITE_SHARE_URL,noticeId,title);
                dialog.dismiss();
                break;
            case R.id.tv_share_wx_friend:
                shareAction(SHARE_MEDIA.WEIXIN, mContext, ConfigUtil.INVTITE_SHARE_URL, noticeId, title);
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
            case R.id.tv_qiangdan_yr:
                tvQiangdanYR.setTextColor(getResources().getColor(R.color.color_4b3a75));
                tvTuijianYR.setTextColor(getResources().getColor(R.color.text_color_9));
                setIndexSelected(0);
                break;
            case R.id.tv_tuijian_yr:
                tvQiangdanYR.setTextColor(getResources().getColor(R.color.text_color_9));
                tvTuijianYR.setTextColor(getResources().getColor(R.color.color_4b3a75));
                setIndexSelected(1);
                break;
        }
    }

    private void initFragment() {
        QiangdanFragment fragment1 = new QiangdanFragment();
        TuijianFragment fragment2 = new TuijianFragment();

        mFragments = new Fragment[]{fragment1, fragment2};
        //开始事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.layout_container, fragment1).commit();
        //默认设置为第0个
        setIndexSelected(0);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.layout_container, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
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

    public String getNoticeId() {
        return noticeId;
    }
    public String getProvince() {

        return province;
    }
    public String getCity() {

        return city;
    }

    public int getType() {

        return type;
    }
}
