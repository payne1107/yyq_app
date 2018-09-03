package com.yyq58.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lidroid.xutils.db.annotation.Check;
import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zaaach.citypicker.CityPickerActivity;
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
        TextView tvLocation =findViewById(R.id.tv_location);
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
        }
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
