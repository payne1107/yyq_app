package com.yyq58.activity.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yyq58.activity.httpstack.OkHttpStack;

import java.io.File;

public class MyApplication extends Application{
    public static final String REQUEST_URL = "https://wxapi.yyq58.com/";
    /** 相册选择 */
    public static final int SELECT_PICTURE_CODE = 1;
    public static final int SLECT_CARMEA_CODE = 2;
    public static final int REQUEST_CODE_RESULT = 3;
    public static final int REQUEST_CODE_PREVIEW = 4;
    //图片缓存路径
    public static final String dirname = "yyq_app";
    public static final String dirname_cache = "/" + dirname + "/cache/";
    //网络框架
    public static RequestQueue mRequestQueue;
    //展示图片
    public static ImageLoader imageLoader;
    //是否已经登陆
    public static String isLogin = "";
    //用户的userId
    public static String userId = "";
    //用户的邀请码
    public static String inviteCode = "";

    private static MyApplication mInstance;

    public static MyApplication getApplication() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
        createVolley();
    }

    //配置网络框架
    private void createVolley() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }
    }

    /***
     * 初始化图片框架
     * @param context
     * @return
     */
    public static ImageLoader initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, dirname_cache);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCacheExtraOptions(480, 800)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .threadPoolSize(3)//线程池内加载的数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheExtraOptions(480, 320, null)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        return imageLoader;
    }

    /***
     * @author Dong
     * 创建图片的存储路径WBShareActivity
     */
    public static String getImageFolderPath() {
        String path = Environment.getExternalStorageDirectory() + File.separator + "sgz"
                + File.separator + "data" +
                File.separator + "APP_FOLDER_NAME" +
                File.separator + "MY_FLODER_NAME"
                + File.separator;
        if (makeDirs(path)) {
            return path;
        }
        return null;
    }

    public static boolean makeDirs(String path) {
        File dir = new File(path);
        return dir.exists() || dir.mkdirs();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
