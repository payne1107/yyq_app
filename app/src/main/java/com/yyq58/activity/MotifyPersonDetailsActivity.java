package com.yyq58.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;
import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.widget.CircleImageView;

import java.util.Arrays;
import java.util.List;

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
    private Context mContext;
    private Dialog chooseDialog;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_motify_person_details);
        mContext = MotifyPersonDetailsActivity.this;
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
                String[] arrayJobCategory = getResources().getStringArray(R.array.array_sex_category);
                showChooseDialog(Arrays.asList(arrayJobCategory));
                break;
            case R.id.tv_work_time:
                //选择从业时间
                break;
        }
    }

    /****
     * 选择求职设置对话框
     */
    private void showChooseDialog(final List<String> mlist) {
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.choose_job_setting_dialog, null);
        chooseDialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        chooseDialog.setContentView(view1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = chooseDialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        chooseDialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        chooseDialog.setCanceledOnTouchOutside(true);
        LoopView loopView = chooseDialog.findViewById(R.id.loopView);
        TextView tvConfrim = chooseDialog.findViewById(R.id.tv_confirm);
        TextView tvCancel = chooseDialog.findViewById(R.id.tv_cancel);
        //设置数据
        loopView.setItems(mlist);
        chooseDialog.show();

        tvCancel.setOnClickListener(this);
        tvConfrim.setOnClickListener(this);
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                String labelName = mlist.get(index);

            }
        });
    }

}
