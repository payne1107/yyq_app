package com.yyq58.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.SDCardUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    protected static final int REQUEST_CHOOSE_QCQUIREMENT_CODE = 1000;
    private TextView tvGetCode;
    private EditText etPhone;
    boolean isRun = true;//是否在获取验证码中
    private int count;
    private TextView tvRegister;
    private EditText etNickName;
    private EditText etPwd;
    private EditText etVerifyCode;
    private Context mContext;
    private TextView tvAcquirement;
    private CircleImageView ivAvatar;
    // 裁剪之后保存图片到新路径 专用头像路径，更换后会被替换头像
    private String newPath = MyApplication.getImageFolderPath() + ".png";
    private String labelId;
    private String labelName;
    private String avatarUrl;//存储头像

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        mContext = RegisterActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("手机快速注册", true, true);
        tvGetCode = (TextView) findViewById(R.id.tv_verify_code);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvRegister = findViewById(R.id.tv_register);
        etNickName = findViewById(R.id.et_nick_name);
        etVerifyCode = findViewById(R.id.et_verify_code);
        etPwd = findViewById(R.id.et_pwd);
        tvAcquirement = findViewById(R.id.tv_acquirement);
        ivAvatar = findViewById(R.id.iv_avatar);

        setListener();
    }

    private void setListener() {
        tvGetCode.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvAcquirement.setOnClickListener(this);
        ivAvatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_verify_code:
                getCheckCode();
                break;
            case R.id.tv_register:
                register();
                break;
            case R.id.tv_acquirement:
                //选择才艺
                startActivityForResult(new Intent(mContext, ChooseAcquirementActivity.class), REQUEST_CHOOSE_QCQUIREMENT_CODE);
                break;
            case R.id.iv_avatar:
                //选择头像
                choosePhoto();
                break;
        }
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
                    tvAcquirement.setText(StringUtils.isEmpty(labelName) ? "" : labelName);
                }
                break;
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
                        ivAvatar.setImageBitmap(bitmap);
                        SDCardUtil.saveBitmap(bitmap, newPath);
                        uploadImg(MyApplication.userId, newPath, ConfigUtil.UPLOAD_PIC_URL, "avatar");
                    }
                }
                break;
        }
    }

    /****
     * 注册
     */
    private void register() {
        String phone = etPhone.getText().toString().trim();
        String nickName = etNickName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        String verifyCode = etVerifyCode.getText().toString().trim();
        String acquirement = tvAcquirement.getText().toString().trim();
        if (StringUtils.isEmpty(phone) || phone.length() != 11) {
            toastMessage("手机号格式不正确");
            return;
        }
        if (StringUtils.isEmpty(verifyCode)) {
            toastMessage("验证码不能为空");
            return;
        }
        if (StringUtils.isEmpty(nickName)) {
            toastMessage("昵称不能为空");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            toastMessage("密码不能为空");
            return;
        }
        if ("请选择才艺".equals(acquirement) || "".equals(acquirement)) {
            toastMessage("请选择才艺");
            return;
        }
        if (StringUtils.isEmpty(avatarUrl)) {
            toastMessage("请上传头像");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("code", verifyCode);
        params.put("pwd", pwd);
        params.put("nickName", nickName);
        params.put("avatar", avatarUrl);
        params.put("labelId", labelId);
        params.put("labelName", labelName);
        httpPostRequest(ConfigUtil.REGISTER_URL, params, ConfigUtil.REGISTER_URL_ACTION);
    }


    /***
     * 获取验证码
     */
    private void getCheckCode() {
        String userPhone = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(userPhone) || userPhone.length() != 11) {
            toastMessage("请输入正确的手机号");
            return;
        }
        codeUnClick();
        Map<String, String> params = new HashMap<>();
        params.put("userPhone", userPhone);
        httpPostRequest(ConfigUtil.GET_REGISTER_SMS_URL, params, ConfigUtil.GET_REGISTER_SMS_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.GET_REGISTER_SMS_URL_ACTION:

                break;
            case ConfigUtil.REGISTER_URL_ACTION:
                toastMessage("注册成功");
                finish();
                break;
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

    /**
     * 获取验证码不能点击
     */
    private void codeUnClick() {
        tvGetCode.setTextColor(getResources().getColor(R.color.text_color_9));
        tvGetCode.setClickable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    count++;
                    Message msg = Message.obtain();
                    msg.arg1 = count;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count >= 59) {
                        isRun = false;
                    }
                }
                Message msg = Message.obtain();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 != -1) {
                int i = 60 - msg.arg1;
                tvGetCode.setText(i + "s后重发");
            } else {
                tvGetCode.setText("获取验证码");
                tvGetCode.setTextColor(getResources().getColor(R.color.white));
                tvGetCode.setClickable(true);
                count = 0;
                isRun = true;
            }
        }
    };


    @Override
    protected void getImageUrl(String url) {
        super.getImageUrl(url);
        avatarUrl = url;
    }
}
