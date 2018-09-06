package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lidroid.xutils.db.annotation.Check;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.SDCardUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.yyq58.activity.RegisterActivity.REQUEST_CHOOSE_QCQUIREMENT_CODE;

/****
 * 发布通告
 */
public class ReleaseNoticeActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private TextView tvTime;
    private TextView tvLocation;
    private EditText etDetailsLocation;
    private EditText etPrice;
    private CheckBox checkBox;
    private EditText etContent;
    private AutoLinearLayout layoutSave;
    private TextView tvCategory;
    private TimePickerDialog dialogDay;
    private String province;
    private String city;
    private String district;
    private Context mContext;
    private String labelId;
    private String labelName;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_notice);
        mContext = ReleaseNoticeActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("发布通告",true,true);
        initViewDateDialog(this, System.currentTimeMillis() - ConfigUtil.TenYears);

        tvCategory = findViewById(R.id.tv_category);
        tvTime = findViewById(R.id.tv_time);
        tvLocation = findViewById(R.id.tv_location);
        etDetailsLocation = findViewById(R.id.et_details_location);
        etPrice = findViewById(R.id.et_price);
        checkBox = findViewById(R.id.checkbox_price);
        etContent = findViewById(R.id.et_content);
        layoutSave = findViewById(R.id.layout_save);

        setListener();
    }

    private void setListener() {
        tvCategory.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        tvLocation.setOnClickListener(this);
        layoutSave.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    etPrice.setText("");
                    etPrice.setEnabled(false);
                } else {
                    etPrice.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_category:
                //选择分类
                startActivityForResult(new Intent(mContext, ChooseAcquirementActivity.class), REQUEST_CHOOSE_QCQUIREMENT_CODE);
                break;
            case R.id.tv_time:
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.tv_location:
                showChooseCityDialog();
                break;
            case R.id.layout_save:
                releaseNotice();
                break;
        }
    }

    /****
     * 发布通告
     */
    private void releaseNotice() {
        Map<String, String> params = new HashMap<>();

    }


    /**
     * 初始化时间选择器
     */
    protected void initViewDateDialog(OnDateSetListener listener, long currentMillSeconds) {
        dialogDay = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - ConfigUtil.TenYears8)
                .setMaxMillseconds(System.currentTimeMillis() +   ConfigUtil.TenYears)
                .setCurrentMillseconds(currentMillSeconds /*System.currentTimeMillis() - ConfigUtil.TenYears*/)//设置当前日期
                .setThemeColor(getResources().getColor(R.color.cccccc))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSize(16)
                .build();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        tvTime.setText(sf.format(d));
    }

    /***
     * 选择城市对话框
     */
    private void showChooseCityDialog() {
        CityPicker cityPicker = new CityPicker.Builder(ReleaseNoticeActivity.this).textSize(16) //滚轮文字的大小
                .title("城市选择") //标题，设置名称
                .titleBackgroundColor("#fafafa") //标题背景
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("北京市")
                .city("北京市")
                .district("朝阳区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();
        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                province = citySelected[0];
                city = citySelected[1];
                district = citySelected[2];
                tvLocation.setText(province + "-" + city + "-" + district);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHOOSE_QCQUIREMENT_CODE:
                //选择才艺
                if (data != null) {
                    labelId = data.getStringExtra("laeblId");
                    labelName = data.getStringExtra("labelName");
                    tvCategory.setText(StringUtils.isEmpty(labelName) ? "" : labelName);
                }
                break;
        }
    }
}
