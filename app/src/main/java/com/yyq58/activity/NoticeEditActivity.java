package com.yyq58.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * 通告编辑
 */
public class NoticeEditActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private AutoLinearLayout layoutSave;
    private CheckBox checkBox;
    private EditText etPrice;
    private String price;
    private TimePickerDialog dialogDay;
    private TextView tvTime;
    private String noticeId;
    private TextView tvLocation;
    private Context mContext;
    private EditText etPersonNum;
    private String manyuan;
    private EditText etContent;
    private String district;
    private String city;
    private String province;
    private EditText etDetailsLocation;
    private int mianyi =0;
    private String title;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notice_edit);
        mContext = NoticeEditActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("通告编辑", true, true);

        initViewDateDialog(this, System.currentTimeMillis() - ConfigUtil.TenYears);

        TextView tvCategory = findViewById(R.id.tv_category);
        etPersonNum = findViewById(R.id.et_person_num);
        tvTime = findViewById(R.id.tv_time);
        tvLocation = findViewById(R.id.tv_location);
        etDetailsLocation = findViewById(R.id.et_details_location);
        etPrice = findViewById(R.id.et_price);
        checkBox = findViewById(R.id.checkbox_price);
        etContent = findViewById(R.id.et_content);
        layoutSave = findViewById(R.id.layout_save);

        String labelName = getIntent().getStringExtra("labelName");
        noticeId = getIntent().getStringExtra("noticeId");
        String time =getIntent().getStringExtra("time");
        String location =getIntent().getStringExtra("location");
        String detailsPlace =getIntent().getStringExtra("detailsPlace");
        price = getIntent().getStringExtra("price");
        String content = getIntent().getStringExtra("content");
        int num = getIntent().getIntExtra("num", 0);
        manyuan = getIntent().getStringExtra("manyuan");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        district = getIntent().getStringExtra("county");
        title = getIntent().getStringExtra("title");
        String mianyi = getIntent().getStringExtra("mianyi");

        if (("1").equals(mianyi)) {
            //面议
            checkBox.setChecked(true);
            etPrice.setText("");
            etPrice.setEnabled(false);
        } else {
            etPrice.setText(StringUtils.isEmpty(price) ? "" : price);
            checkBox.setChecked(false);
            etPrice.setEnabled(true);
        }
        tvCategory.setText(StringUtils.isEmpty(labelName) ? "" : labelName);
        etPersonNum.setText("" + num);
        tvTime.setText(StringUtils.isEmpty(time) ? "" : time);
        tvLocation.setText(StringUtils.isEmpty(location) ? "" : location);
        etDetailsLocation.setText(StringUtils.isEmpty(detailsPlace) ? "" : detailsPlace);
        etPrice.setText(StringUtils.isEmpty(price) ? "" : price);
        etContent.setText(StringUtils.isEmpty(content) ? "" : content);

        seetListener();
    }

    private void seetListener() {
        layoutSave.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        tvLocation.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mianyi = 1;
                    etPrice.setText("");
                    etPrice.setEnabled(false);
                } else {
                    mianyi = 0;
                    etPrice.setEnabled(true);
                    etPrice.setText(StringUtils.isEmpty(price) ? "" : price);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_save:
                //保存
                editNotice(noticeId);
                break;
            case R.id.tv_time:
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.tv_location:
                //选择城市
                showChooseCityDialog();
                break;
        }
    }

    /****
     * 通告编辑
     * @param noticeId 通告id
     */
    private void editNotice(String noticeId) {
        String num = etPersonNum.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        String detailLocation =etDetailsLocation.getText().toString().trim();
        String price =etPrice.getText().toString().trim();
        String time = tvTime.getText().toString();
        if (StringUtils.isEmpty(detailLocation)) {
            toastMessage("详细地址不能为空");
            return;
        }
        if (StringUtils.isEmpty(content)) {
            toastMessage("内容简介不能为空");
            return;
        }
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("noticeId", noticeId);
        params.put("consumerId", MyApplication.userId);
        params.put("labelName", title);
        params.put("num", num);
        params.put("manyuan", manyuan);
        params.put("content", content);
        params.put("province", province);
        params.put("city", city);
        params.put("county", district);
        params.put("detailPlace", detailLocation);
        params.put("mianyi", String.valueOf(mianyi));
        params.put("time", time);
        if (mianyi == 0) {
            params.put("price", price);
        }
        httpPostRequest(ConfigUtil.EDIT_NOTICE_URL, params, ConfigUtil.EDIT_NOTICE_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.EDIT_NOTICE_URL_ACTION:
                handleEditNotice(json);
                break;
        }
    }

    /***
     * 处理编辑通告
     * @param json
     */
    private void handleEditNotice(String json) {
        if (getRequestCode(json) == 1000) {
            toastMessage("保存成功");
            finish();
        }
    }

    /***
     * 选择城市对话框
     */
    private void showChooseCityDialog() {
        CityPicker cityPicker = new CityPicker.Builder(NoticeEditActivity.this).textSize(16) //滚轮文字的大小
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
}
