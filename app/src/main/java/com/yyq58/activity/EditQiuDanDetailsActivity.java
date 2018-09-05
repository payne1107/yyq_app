package com.yyq58.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
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
 * 编辑求单
 */
public class EditQiuDanDetailsActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private RadioGroup radioGroup;
    private int resonId = 1; //编辑求单原因 默认为1
    private AutoLinearLayout layoutSave;
    private String noticeId;
    private String labelId;
    private TextView tvTime;
    private TextView tvCategory;
    private TextView tvLocation;
    private EditText etPrice;
    private EditText etContent;
    private CheckBox checkBox;
    private String province;
    private String city;
    private String district;
    private TimePickerDialog dialogDay;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_qiudan_details);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("求单编辑", true, true);
        initViewDateDialog(this, System.currentTimeMillis() - ConfigUtil.TenYears);
        String typename = getIntent().getStringExtra("typeName");
        String time = getIntent().getStringExtra("time");
        province =getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        district =getIntent().getStringExtra("county");
        String price = getIntent().getStringExtra("price");
        String content =getIntent().getStringExtra("content");
        noticeId = getIntent().getStringExtra("noticeId");
        labelId = getIntent().getStringExtra("labelId");
        String mianyi = getIntent().getStringExtra("mianyi");

        tvCategory = findViewById(R.id.tv_category);
        tvTime = findViewById(R.id.tv_time);
        tvLocation = findViewById(R.id.tv_location);
        etPrice = findViewById(R.id.et_price);
        etContent = findViewById(R.id.et_content);
        checkBox = findViewById(R.id.checkbox_price);
        radioGroup = findViewById(R.id.radioGroup);
        layoutSave = findViewById(R.id.layout_save);
        tvCategory.setText(StringUtils.isEmpty(typename) ? "" : typename);
        tvTime.setText(StringUtils.isEmpty(time) ? "" : time);
        tvLocation.setText(province + " " + city + " " + district);
        etContent.setText(StringUtils.isEmpty(content) ? "" : content);
        if (mianyi.equals("1")) {
            //面议
            checkBox.setChecked(true);
            etPrice.setText("");
            etPrice.setEnabled(false);
        } else {
            etPrice.setText(StringUtils.isEmpty(price) ? "" : price);
            checkBox.setChecked(false);
            etPrice.setEnabled(true);
        }

        setListener();
    }




    private void setListener() {
        layoutSave.setOnClickListener(this);
        tvLocation.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    etPrice.setEnabled(false);
                } else {
                    etPrice.setEnabled(true);
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.radioButton1:
                        resonId = 1;
                        break;
                    case R.id.radioButton2:
                        resonId = 2;
                        break;
                    case R.id.radioButton3:
                        resonId = 3;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_save:
                saveQiudanEdit();
                break;
            case R.id.tv_time:
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.tv_location:
                showChooseCityDialog();
                break;
        }
    }

    /****
     * 保存求单编辑请求
     */
    private void saveQiudanEdit() {
        String time = tvTime.getText().toString().trim();
        String price =etPrice.getText().toString().trim();
        String content =etContent.getText().toString().trim();
        if (StringUtils.isEmpty(time)) {
            toastMessage("请选择时间");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("labelId", labelId);
        params.put("workTime", time);
        params.put("province", province);
        params.put("city", city);
        params.put("county", district);
        if (checkBox.isChecked()) {
            params.put("mianyi", "1");
        } else {
            params.put("mianyi", "0");
            if (StringUtils.isEmpty(price)) {
                toastMessage("金额不能为空");
                params.put("price", price);
                return;
            }
        }
        params.put("remark", content);
        params.put("reason", String.valueOf(resonId));
        params.put("id", noticeId);
        httpPostRequest(ConfigUtil.EDIT_QIUDAN_URL, params, ConfigUtil.EDIT_QIUDAN_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.EDIT_QIUDAN_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("编辑成功");
                    finish();
                }
                break;
        }
    }

    /***
     * 选择城市对话框
     */
    private void showChooseCityDialog() {
        CityPicker cityPicker = new CityPicker.Builder(EditQiuDanDetailsActivity.this).textSize(16) //滚轮文字的大小
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
