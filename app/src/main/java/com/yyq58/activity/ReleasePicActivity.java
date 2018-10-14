package com.yyq58.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanchen.compresshelper.CompressHelper;
import com.yyq58.R;
import com.yyq58.activity.adapter.HorizontalListViewAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.Bimp;
import com.yyq58.activity.utils.CacheImgUtil;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.SDCardUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.HorizontalListView;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yyq58.activity.application.MyApplication.PHOTO_PATH;

/***
 * 发布图片
 */
public class ReleasePicActivity extends BaseActivity implements View.OnClickListener {
    private AutoLinearLayout layoutRelease;
    private EditText etFeedBack;
    private ImageView ivCamera;
    private Context mContext;
    private Dialog dialog;
    private HorizontalListView listView;
    private HorizontalListViewAdapter adapter;
    public static List<String> listUploadImgUrl = new ArrayList<>();//存储上传成功后返回的图片url

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_pic);
        mContext = ReleasePicActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("发表图片", true, true);
        layoutRelease = (AutoLinearLayout) findViewById(R.id.layout_release);
        etFeedBack = (EditText) findViewById(R.id.et_feedback);
        ivCamera = (ImageView) findViewById(R.id.iv_camera);
        listView = (HorizontalListView) findViewById(R.id.horizontal_listview);
        adapter = new HorizontalListViewAdapter(this);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        ivCamera.setOnClickListener(this);
        layoutRelease.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,PhotoActivity.class);
                intent.putExtra("ID", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_camera:
                if (Bimp.drr.size() >= 3) {
                    toastMessage("只能上传三张");
                    return;
                }
                showSeclectPhotoDialog();
                break;
            case R.id.tv_cancel_photo:
                dialog.dismiss();
                break;
            case R.id.tv_select_photo:
                choosePhoto();
                dialog.dismiss();
                break;
            case R.id.tv_take_photo:
                if (isGetPermission(Manifest.permission.CAMERA)) {
                    showCamera();
                } else {
                    startPhoto();
                }
                dialog.dismiss();
                break;
            case R.id.layout_release:
                String feedBack = etFeedBack.getText().toString().trim();
                if (StringUtils.isEmpty(feedBack)) {
                    toastMessage("说点什么吧..");
                    return;
                }
                if (Bimp.drr.size() < 1) {
                    toastMessage("上传一张图片吧..");
                } else {
                    releaseDynamic(feedBack);
                }
                break;
        }
    }

    /***
     * 发布动态
     */
    private void releaseDynamic(String content) {
        String urls = StringUtils.listToString(listUploadImgUrl, ',');
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("content", content);
        params.put("urls", urls);
        params.put("flag", "1");
        params.put("isVideo", "0");
        startIOSDialogLoading(mContext, "");
        httpPostRequest(ConfigUtil.RELEASE_PIC_URL, params, ConfigUtil.RELEASE_PIC_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.RELEASE_PIC_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("上传成功");
                    finish();
                }
                break;
        }
    }

    /***
     * 打开相机拍照
     */
    private void startPhoto() {
        if(SDCardUtil.cheekSDCardIsMounted()) {
            Intent intentToCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intentToCamera, MyApplication.SLECT_CARMEA_CODE);
        } else {
            toastMessage(getString(R.string.str_check_sd_card));
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.update();
        }
    }

    /***
     * 选择相册
     */
    private void goSeePhoto() {
//        Intent intent = new Intent(mContext, ChoosePicActivity.class);
//        startActivity(intent);
//        dialog.dismiss();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case MyApplication.SLECT_CARMEA_CODE: //相机拍照
                    Bitmap bitmap;
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        bitmap = bundle.getParcelable("data");
                        //将bitmap保存到本地，下次获取可以从本地获取展示，之后在从网络取出显示
                        SDCardUtil.saveBitmap(bitmap, PHOTO_PATH);
                        File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(new File(PHOTO_PATH));
                        uploadImg(MyApplication.userId, "", ConfigUtil.UPLOAD_PIC_URL, "zone", newFile);
                        if (Bimp.drr.size() <3) {
                            Bimp.drr.add(PHOTO_PATH);
                            //Bimp.bmp.add(bitmap);
                        }
                    }
                    break;
                case MyApplication.SELECT_PICTURE_CODE:
                    Uri imageUrl = data.getData();
                    String photoPath = CacheImgUtil.getImageAbsolutePath(mContext, imageUrl);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(new File(photoPath));
                    uploadImg(MyApplication.userId, "", ConfigUtil.UPLOAD_PIC_URL, "zone", newFile);
                    if (Bimp.drr.size() <3) {
                        Bimp.drr.add(photoPath);
                        //Bimp.bmp.add(bitmap);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Bimp.bmp.clear();
        Bimp.drr.clear();
        Bimp.max = 0;
    }

    /**
     * 选着照片和拍照的Dialog
     * @author Dong
     ***/
    private void showSeclectPhotoDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.photo_choose_dialog, null);
        dialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = this.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView btnCancel = (TextView) view.findViewById(R.id.tv_cancel_photo);
        TextView btnTakePhoto = (TextView) view.findViewById(R.id.tv_take_photo);
        TextView btnSelectPhoto = (TextView) view.findViewById(R.id.tv_select_photo);
        btnCancel.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);
        btnSelectPhoto.setOnClickListener(this);
    }

    @Override
    protected void getImageUrl(String url) {
        super.getImageUrl(url);
        if (!StringUtils.isEmpty(url)) {
            listUploadImgUrl.add(url);
        }
    }
}
