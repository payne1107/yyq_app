package com.yyq58.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;
import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.SDCardUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.MyDialog;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 修改用户信息
 */
public class MotifyPersonDetailsActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

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
    private String province;
    private String city;
    private String district;
    // 裁剪之后保存图片到新路径 专用头像路径，更换后会被替换头像
    private String newPath = MyApplication.getImageFolderPath() + ".png";
    private TimePickerDialog dialogDay;

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
        tvSet.setTextColor(getResources().getColor(R.color.white));
        initViewDateDialog(this, System.currentTimeMillis() - ConfigUtil.TenYears);

        String nickname = getIntent().getStringExtra("nickname");
        String truename = getIntent().getStringExtra("truename");
        String avatar = getIntent().getStringExtra("avatar");
        String sex = getIntent().getStringExtra("sex");
        String motto = getIntent().getStringExtra("motto");
        String phonenumber = getIntent().getStringExtra("phonenumber");
        String labelname = getIntent().getStringExtra("labelName");
        String height = getIntent().getStringExtra("height");
        String weight = getIntent().getStringExtra("weight");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        district = getIntent().getStringExtra("county");
        String workTime = getIntent().getStringExtra("workTime");



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

        if (!StringUtils.isEmpty(avatar)) {
            MyApplication.imageLoader.displayImage(avatar, ivAvatar);
        }
        tvChooseCity.setText(province + " " + city + " " + district);
        tvChooseCYType.setText(StringUtils.isEmpty(labelname) ? "" : labelname);
        tvChooseSex.setText(StringUtils.isEmpty(sex) ? "" : sex);
        tvWorkTime.setText(StringUtils.isEmpty(workTime) ? "" : workTime);
        etPhone.setText(StringUtils.isEmpty(phonenumber) ? "" : phonenumber);
        etNickname.setText(StringUtils.isEmpty(nickname) ? "" : nickname);
        etName.setText(StringUtils.isEmpty(truename) ? "" : truename);
        etWeight.setText(StringUtils.isEmpty(weight) ? "" : weight);
        etHeight.setText(StringUtils.isEmpty(height) ? "" : height);

        etMotto.setText(StringUtils.isEmpty(motto) ? "" : motto);

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
                uploadPersonInfo();
                break;
            case R.id.iv_avatar:
                //上传头像
                choosePhoto();
                break;
            case R.id.tv_choose_city:
                showChooseCityDialog();
                break;
            case R.id.tv_choose_cy_type:
                //选择才艺
                startActivity(new Intent(mContext, ChooseMultipleAcquirementActivity.class));
                break;
            case R.id.tv_choose_sex:
                String[] arrayJobCategory = getResources().getStringArray(R.array.array_sex_category);
                showChooseDialog(Arrays.asList(arrayJobCategory));
                break;
            case R.id.tv_work_time:
                //选择从业时间
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.tv_cancel:
                chooseDialog.dismiss();
                break;
            case R.id.tv_confirm:
                chooseDialog.dismiss();
                break;
        }
    }

    /***
     * 上传用户信息
     */
    private void uploadPersonInfo() {
        String nickName = etNickname.getText().toString().trim();
        String phone =etPhone.getText().toString().trim();
        String name =etName.getText().toString().trim();
        String height =etHeight.getText().toString().trim();
        String weight  =etWeight.getText().toString().trim();
        String motto =etMotto.getText().toString().trim();
        String sex = tvChooseSex.getText().toString().trim();
        String workTime = tvWorkTime.getText().toString().trim();
        if (StringUtils.isEmpty(nickName) || "请输入".equals(nickName)) {
            toastMessage("请输入昵称");
            return;
        }
        if (StringUtils.isEmpty(phone) || "请输入".equals(phone)) {
            toastMessage("请输入手机号");
            return;
        }
        if (StringUtils.isEmpty(name) || "请输入".equals(name)) {
            toastMessage("请输入真实姓名");
            return;
        }
        if (StringUtils.isEmpty(height) || "请输入".equals(height)) {
            toastMessage("请输入身高");
            return;
        }
        if (StringUtils.isEmpty(weight) || "请输入".equals(weight)) {
            toastMessage("请输入体重");
            return;
        }
        if (StringUtils.isEmpty(motto) || "请输入".equals(motto)) {
            toastMessage("请输入格言");
            return;
        }

        if (StringUtils.isEmpty(sex) || "请选择".equals(sex)) {
            toastMessage("请选择性别");
            return;
        }
        if (StringUtils.isEmpty(workTime) || "请选择".equals(workTime)) {
            toastMessage("请选择从业时间");
            return;
        }
        startIOSDialogLoading(mContext,"上传中..");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("account", nickName);
        params.put("phone", phone);
        params.put("province", province);
        params.put("city", city);
        params.put("county", district);
        params.put("starTime", workTime);
        params.put("trueName", name);
        params.put("sex", sex);
        params.put("height", height);
        params.put("weight", weight);
        params.put("motto", motto);
        httpPostRequest(ConfigUtil.UPDATE_PERSON_INFO_URL,params,ConfigUtil.UPDATE_PERSON_INFO_URL_ACTION);
    }


    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.UPDATE_PERSON_INFO_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("修改成功");
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
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSize(16)
                .build();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        tvWorkTime.setText(sf.format(d));
    }

    /***
     * 选择相册
     */
    private void choosePhoto() {
        if (isGetPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            readExternalStorage();
        } else {
            goSeePhoto();
        }
    }

    private void goSeePhoto() {
        if (isGetPermission(Manifest.permission.CAMERA)) {
            showCamera();
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, MyApplication.SELECT_PICTURE_CODE);
        }
    }

    @Override
    public void handleShowCamera() {
        super.handleShowCamera();
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, MyApplication.SELECT_PICTURE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MyApplication.SELECT_PICTURE_CODE:
                    Uri imageUrl = data.getData();
                    startPhotoZoom(imageUrl, false);
                    break;
                case MyApplication.REQUEST_CODE_RESULT:
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            Bitmap bitmap = bundle.getParcelable("data");
                            //将bitmap保存到本地，下次在获取可以从本地获取展示，之后在从网络获取显示
                            SDCardUtil.saveBitmap(bitmap, newPath);
                            uploadImg("", newPath, ConfigUtil.UPLOAD_PIC_URL, "avatar", null);
                        }
                    }
                    break;
            }
        }
    }

    /***
     * @author Dong 裁剪图片
     */
    public void startPhotoZoom(Uri uri, boolean type) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && type) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        /** outputX 越大，图片越大 */
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, MyApplication.REQUEST_CODE_RESULT);
    }

    @Override
    protected void getImageUrl(String url) {
        super.getImageUrl(url);
        if (!StringUtils.isEmpty(url)) {
            MyApplication.imageLoader.displayImage(url, ivAvatar);
        }
    }

    /****
     * 选择性别设置对话框
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
                tvChooseSex.setText(labelName);
            }
        });
    }

    /***
     * 选择城市对话框
     */
    protected void showChooseCityDialog() {
        CityPicker cityPicker = new CityPicker.Builder(MotifyPersonDetailsActivity.this).textSize(16) //滚轮文字的大小
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
                tvChooseCity.setText(province + "-" + city + "-" + district);
            }
        });
    }
}
