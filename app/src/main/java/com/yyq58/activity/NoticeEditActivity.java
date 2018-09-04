package com.yyq58.activity;

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
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notice_edit);
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
        EditText etPersonNum =findViewById(R.id.et_person_num);
        tvTime = findViewById(R.id.tv_time);
        tvLocation = findViewById(R.id.tv_location);
        EditText etDetailsLocation =findViewById(R.id.et_details_location);
        etPrice = findViewById(R.id.et_price);
        checkBox = findViewById(R.id.checkbox_price);
        EditText etContent =findViewById(R.id.et_content);
        layoutSave = findViewById(R.id.layout_save);

        String labelName = getIntent().getStringExtra("labelName");
        noticeId = getIntent().getStringExtra("noticeId");
        String time =getIntent().getStringExtra("time");
        String location =getIntent().getStringExtra("location");
        String detailsPlace =getIntent().getStringExtra("detailsPlace");
        price = getIntent().getStringExtra("price");
        String content = getIntent().getStringExtra("content");
        int num = getIntent().getIntExtra("num", 0);

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
                    etPrice.setText("");
                } else {
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
                String province = citySelected[0];
                String city = citySelected[1];
                String district = citySelected[2];
                tvLocation.setText(province + "-" + city + "-" + district );
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
