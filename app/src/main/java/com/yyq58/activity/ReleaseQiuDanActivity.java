package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.yyq58.R;
import com.yyq58.activity.adapter.ReleaseQiudanListAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yyq58.activity.RegisterActivity.REQUEST_CHOOSE_QCQUIREMENT_CODE;

/****
 * 发布求单
 */
public class ReleaseQiuDanActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private TextView tvTime;
    private TextView tvLocation;
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
    private GridView gridview;
    private List<String> mListLabelName = new ArrayList<>();
    private ReleaseQiudanListAdapter adapter;
    private List<String> mListLabelId = new ArrayList<>();
    private RadioGroup radioGroup;
    private int resonId = 1; //编辑求单原因 默认为1


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_qiudan);
        mContext = ReleaseQiuDanActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("发布求单",true,true);
        initViewDateDialog(this, System.currentTimeMillis() - ConfigUtil.TenYears);

        tvCategory = findViewById(R.id.tv_category);
        tvTime = findViewById(R.id.tv_time);
        tvLocation = findViewById(R.id.tv_location);
        etPrice = findViewById(R.id.et_price);
        checkBox = findViewById(R.id.checkbox_price);
        etContent = findViewById(R.id.et_content);
        layoutSave = findViewById(R.id.layout_save);
        radioGroup = findViewById(R.id.radioGroup);
        gridview = findViewById(R.id.gridview);
        adapter = new ReleaseQiudanListAdapter(mContext, mListLabelName);
        gridview.setAdapter(adapter);
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
            case R.id.tv_category:
                //选择分类
                startActivityForResult(new Intent(mContext, ChooseMultipleAcquirementActivity.class).putExtra("extra_category_type",1), REQUEST_CHOOSE_QCQUIREMENT_CODE);
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
     * 发布求单
     */
    private void releaseNotice() {
        Map<String, String> params = new HashMap<>();
        if (mListLabelId != null && mListLabelId.size() > 0) {
            String labelId = StringUtils.listToString(mListLabelId, ',');
            params.put("labelIds",labelId);
        } else {
          toastMessage("请选择才艺类别");
        }

        String time = tvTime.getText().toString().trim();
        String location = tvLocation.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        String price = etPrice.getText().toString().trim();

        if ("请选择时间".equals(time)) {
            toastMessage("请选择时间");
            return;
        }
        if ("请选择地区".equals(location)) {
            toastMessage("请选择地区");
            return;
        }
        if (StringUtils.isEmpty(content)) {
            toastMessage("备注不能为空");
            return;
        }
        params.put("workTime", time);
        params.put("province", province);
        params.put("city", city);
        params.put("county", district);
        params.put("remark", content);
        if (StringUtils.isEmpty(price)) {
            params.put("mianyi", "1");
        } else {
            params.put("mianyi", "0");
            params.put("prcie",  price);
        }
        params.put("consumerId", MyApplication.userId);
        params.put("reason", String.valueOf(resonId));
        startIOSDialogLoading(mContext, "");
        httpPostRequest(ConfigUtil.RELEASE_QIUDAN_URL,params,ConfigUtil.RELEASE_QIUDAN_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.RELEASE_QIUDAN_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("发布成功");
                    finish();
                }
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

    /***
     * 选择城市对话框
     */
    private void showChooseCityDialog() {
        CityPicker cityPicker = new CityPicker.Builder(ReleaseQiuDanActivity.this).textSize(16) //滚轮文字的大小
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
                if (data != null) {
                    labelId = data.getStringExtra("laeblId");
                    labelName = data.getStringExtra("labelName");
                    if (!StringUtils.isEmpty(labelId)) {
                        mListLabelId = StringUtils.stringsToList(labelId, ",");
                    }

                    if (!StringUtils.isEmpty(labelName)) {
                        tvCategory.setText(StringUtils.isEmpty(labelName) ? "" : labelName);
                        mListLabelName = StringUtils.stringsToList(labelName, ",");
                    }

                    if (mListLabelName != null && mListLabelName.size() > 0) {
                        adapter.setData(mListLabelName);
                    } else {
                        adapter.setData(mListLabelName);
                    }
                }
                break;
        }
    }
}
