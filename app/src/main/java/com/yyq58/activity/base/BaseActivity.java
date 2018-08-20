package com.yyq58.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yyq58.R;
import com.yyq58.activity.LoginActivity;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.FieldErrors;
import com.yyq58.activity.httpstack.OkHttpStack;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StatusUtils;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.LoadingDailog;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseActivity extends FragmentActivity {

    private Activity mContext;
    //加载对话框
    public LoadingDailog loadingDailog;
    private RelativeLayout mRlChild;
    private TextView tvTitle;
    private ImageView ivBack;
    private TextView tvSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateCustom(savedInstanceState);
        initView();
        initData();
        mContext = BaseActivity.this;
    }

    /***
     * 子类可以实现这个方法填充View
     * @param savedInstanceState
     */
    protected abstract void onCreateCustom(Bundle savedInstanceState);

    /***
     * 子类实现此方法,填充数据
     */
    protected abstract void initData();

    /****
     * 初始化View
     * 子类可以重写此方法
     */
    protected void initView() {
        mRlChild = (RelativeLayout) findViewById(R.id.rl_child);
        tvTitle = (TextView) findViewById(R.id.activity_title);
        ivBack = (ImageView) findViewById(R.id.activity_back);
        tvSet = (TextView) findViewById(R.id.activity_set);
    }

    /************************************************网络请求框架Start*************************************************/
    /**
     * GET请求
     */
    public void httpGetRequest(String url, final int action) {
        if (MyApplication.mRequestQueue == null) {
            MyApplication.mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String content) {
                        httpOnResponse(content, action);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                errorResponseHandler(error.networkResponse == null ? 0 : error.networkResponse.statusCode, error, action);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                if (!StringUtils.isEmpty(ConfigUtil.sessionId))
                    headers.put("Cookie", ConfigUtil.sessionId);
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                // TODO Auto-generated method stub
                try {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    String dataString = new String(response.data, "UTF-8");
                    if (!StringUtils.isEmpty(rawCookies)) {
                        String[] cookies = rawCookies.split(";");
                        if (StringUtils.isEmpty(ConfigUtil.sessionId) && !StringUtils.isEmpty(cookies[0])) {
                            ConfigUtil.sessionId = cookies[0];
                        }
                    }
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        MyApplication.mRequestQueue.add(stringRequest);
    }

    /**
     * post请求
     */
    public void httpPostRequest(String url, final Map<String, String> params, final int action) {
        Log.d("Dong", params.toString());
        Log.d("Dong", "Myapplication =" + MyApplication.isLogin);
        if (MyApplication.mRequestQueue == null)
            MyApplication.mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    public void onResponse(String content) {
                        stopIOSDialogLoading(mContext);
                        httpOnResponse(content, action);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                stopIOSDialogLoading(mContext);
                errorResponseHandler(error.networkResponse == null ? 0 : error.networkResponse.statusCode, error, action);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                headers.put("Accept", "application/json");
                if (!StringUtils.isEmpty(MyApplication.isLogin)) {
                    headers.put("Authorization", MyApplication.isLogin);
                }
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                // TODO Auto-generated method stub
                try {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    String dataString = new String(response.data, "UTF-8");
                    if (!StringUtils.isEmpty(rawCookies)) {
                        String[] cookies = rawCookies.split(";");
                        Log.d("Dong", "cookies ================== " + cookies[0] + " ,,,, cooks ==== " + cookies.toString());
                        if (StringUtils.isEmpty(ConfigUtil.sessionId) && !StringUtils.isEmpty(cookies[0])) {
                            ConfigUtil.sessionId = cookies[0];
                        }
                    }
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        MyApplication.mRequestQueue.add(stringRequest);
    }

    /****
     * 请求异常
     * @param code
     * @param error
     * @param action
     */
    protected void errorResponseHandler(int code, Throwable error, int action) {
        switch (code) {
            case 0:
                Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT).show();
                break;
            case 404:
            case 400:
                Toast.makeText(this, "未找到请求地址", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "code = " + code + " message = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
        httpError(null, action);
    }


    /***
     * 请求返回数据处理
     * @param json 返回的json
     * @param action  标识
     */
    protected void httpOnResponse(String json, int action) {
        Log.e("httpOnResponsejson", json);
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject == null) {
                return;
            }
            int code = (int) jsonObject.get("flag");
            FieldErrors error = null;
            if (code == 0) {
                Object data = jsonObject.get("data");
                if (data instanceof JSONObject) {
                    JSONObject jsonData = (JSONObject) data;
                    String jsonString = jsonData.toJSONString();
                    httpResponse(jsonString, action);
                } else if (data instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) data;
                    String jsonString = jSONArray.toJSONString();
                    httpResponse(jsonString, action);
                } else {
                    httpResponse("" + data, action);
                }
            } else {
                error = JSON.parseObject(json, FieldErrors.class);
                if (error != null) {
                    httpError(error, action);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回Error对象
     * @param error
     * @param action
     */
    protected void httpError(FieldErrors error, int action) {
        if (null != error) {
            if (error.msg.contains("token")) {
                //如果包含token字段需要重新登录
                //clearAppData();
                startActivity(new Intent(mContext, LoginActivity.class));
            } else {
                Toast.makeText(this, error.msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /****
     * 返回数据
     * @param jsonString
     * @param action
     */
    protected void httpResponse(String jsonString, int action) {

    }

    /***
     * 上传头像
     * @param userId
     * @param photoPath
     */
    public void uploadImg(String userId, String photoPath) {
        startIOSDialogLoading(mContext, "正在上传中..");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configResponseTextCharset("UTF-8");
        RequestParams params = new RequestParams();
        params.addBodyParameter("file", new File(photoPath));
        params.addBodyParameter("userId", userId);
        params.addHeader("Accept", "application/json");
        if (!StringUtils.isEmpty(MyApplication.isLogin)) {
            params.addHeader("Authorization", MyApplication.isLogin);
        }
        httpUtils.send(HttpRequest.HttpMethod.POST, "---------------------->", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                stopIOSDialogLoading(mContext);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                stopIOSDialogLoading(mContext);
                Toast.makeText(BaseActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();//请求上传图片接口失败
            }
        });
    }

    protected void getImageUrl(String url) {

    }

    /****
     * 上传多张图片和内容
     * @param path 图片路径
     * @param userId 用户Id
     * @param taskId 任务Id
     * @param stepId 步骤
     * @param contents 内容
     * @param  attendId 报名成功之后返回的id
     */
    public void uploadImg(List<String> path, String userId, int taskId, Set<String> stepId, List<String> contents, String attendId) {
        List<KeyValue> listPath = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            listPath.add(new KeyValue("file", new File(path.get(i))));
        }
        org.xutils.http.RequestParams par = new org.xutils.http.RequestParams("------------------------------------>>>>>>");
        MultipartBody body = new MultipartBody(listPath, "utf-8");
        par.setRequestBody(body);
        par.setMultipart(true);
        par.addBodyParameter("taskId", String.valueOf(taskId));
        par.addBodyParameter("userId", userId);
        par.addHeader("Accept", "application/json");
        if (!StringUtils.isEmpty(MyApplication.isLogin)) {
            par.addHeader("Authorization", MyApplication.isLogin);
        }
        //遍历取出内容
        for (int i = 0; i < contents.size(); i++) {
            par.addParameter("contents", contents.get(i));
        }
        //遍历步骤Id
        Iterator<String> it = stepId.iterator();
        while (it.hasNext()) {
            par.addParameter("stepId", it.next());
        }
        par.addBodyParameter("attendId", attendId);
        Log.d("Dong", "入参-----> " + par.toString());
        startIOSDialogLoading(mContext, "正在上传中..");
        x.http().post(par, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                stopIOSDialogLoading(mContext);
                getImageUrl(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                stopIOSDialogLoading(mContext);
                Log.d("Dong", "上传失败----》" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                stopIOSDialogLoading(mContext);
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
    /************************************************网络请求框架End*************************************************/

    /***
     * 终止正在加载对话框
     * @param  activity 上下文
     */
    public void stopIOSDialogLoading(final Activity activity) {
        if (activity.isFinishing()) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingDailog != null && loadingDailog.isShowing() && !activity.isFinishing()) {
                    loadingDailog.dismiss();
                    loadingDailog = null;
                }
            }
        }, 500);
    }

    /****
     * 启动对话框
     * @param mContext
     */
    public void startIOSDialogLoading(Context mContext, String msg) {
        LoadingDailog.Builder builder = new LoadingDailog.Builder(mContext)
                .setMessage(msg)
                .setCancelable(false).setCancelOutside(true);
        if (null == loadingDailog) {
            loadingDailog = builder.create();
            if (!loadingDailog.isShowing()) {
                loadingDailog.show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        if (mRlChild != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                        || StatusUtils.MIUISetStatusBarLightMode(this.getWindow(), true)
                        || StatusUtils.FlymeSetStatusBarLightMode(this.getWindow(), true)) {
                    StatusUtils.StatusBarLightMode(this);
                    StatusUtils.setStatusBarColor(this, R.color.white);
                    setStatusBar();
                } else {
                    StatusUtils.setStatusBarColor(this, R.color.white);
                    setStatusBar();
                }
            }
        }
    }
    /**
     * android 6.0 设置状态栏颜色
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setStatusBar() {
        if (mRlChild == null) {
            return;
        }
        if (StatusUtils.MIUISetStatusBarLightMode(this.getWindow(), true)) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= 21) {
                window.setStatusBarColor(getResources().getColor(R.color.white));//状态栏颜色
            }
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRlChild.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
        } else {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRlChild.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        if (statusBarHeight == 0) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }

    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.finish_activity_back_in, R.anim.finish_activity_back_out);
    }

    /***
     * 隐藏标题栏图标
     * @param title  标题
     * @param isVisible 是否隐藏图标 True 隐藏 false 显示
     * @param  isVisibleBack 是否显示返回键
     *
     */
    public void setInVisibleTitleIcon(String title, boolean isVisible, boolean isVisibleBack) {
        if (isVisible) {
            tvTitle.setCompoundDrawables(null, null, null, null);
        }
        if (isVisibleBack) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
        tvTitle.setText(title);
    }

    /****
     * 弹出Toast
     * @param message
     */
    public void toastMessage(String message) {
        View toastLayout = getLayoutInflater().inflate(R.layout.sys_toast, null);
        TextView tv = (TextView) toastLayout.findViewById(R.id.toast);
        tv.setText(message);
        final Toast toast = new Toast(getApplicationContext());
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
//        toast.setGravity(Gravity.BOTTOM, 0, height / 3);
        toast.setView(toastLayout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
