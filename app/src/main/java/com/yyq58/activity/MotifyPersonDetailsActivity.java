package com.yyq58.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.widget.CircleImageView;

/***
 * 修改用户信息
 */
public class MotifyPersonDetailsActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView ivAvatar;
    private EditText etNickname;
    private EditText etPhone;
    private TextView tvChooseCYType;
    private TextView etName;
    private TextView tvChooseCity;
    private TextView tvChooseSex;
    private TextView tvWorkTime;
    private EditText etHeight;
    private EditText etWeight;
    private EditText etMotto;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_motify_person_details);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("修改个人资料", true, true);
        tvSet.setVisibility(View.VISIBLE);
        tvSet.setText("提交");

        ivAvatar = findViewById(R.id.iv_avatar);
        etNickname = findViewById(R.id.et_nick_name);
        etPhone = findViewById(R.id.et_phone);
        tvChooseCYType = findViewById(R.id.tv_choose_cy_type);
        etName = findViewById(R.id.et_name);
        tvChooseCity = findViewById(R.id.tv_choose_city);
        tvChooseSex = findViewById(R.id.tv_choose_sex);
        tvWorkTime = findViewById(R.id.tv_work_time);
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        etMotto = findViewById(R.id.et_motto);

        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
        tvChooseCYType.setOnClickListener(this);
        tvChooseCity.setOnClickListener(this);
        tvChooseSex.setOnClickListener(this);
        tvWorkTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                //修改资料
                break;
            case R.id.iv_avatar:

                break;
            case R.id.tv_choose_city:

                break;
            case R.id.tv_choose_cy_type:
                //选择才艺
                break;
            case R.id.tv_choose_sex:

                break;
            case R.id.tv_work_time:
                //选择从业时间
                break;
        }
    }
}
