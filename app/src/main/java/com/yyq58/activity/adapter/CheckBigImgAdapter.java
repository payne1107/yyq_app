package com.yyq58.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yyq58.R;

import java.util.List;

/**
 * Created by 92457 on 2018/3/9.
 * 大图查看器适配
 */

public class CheckBigImgAdapter extends PagerAdapter {

    private List<Object> mList;
    private Context mContext;
    private Activity mActivity;

    public CheckBigImgAdapter(List<Object> list, Context context, Activity activity) {
        this.mList = list;
        this.mContext = context;
        this.mActivity = activity;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_check_big_img, null);
        container.addView(view);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //使用Gilde展示图片
        Glide.with(mContext).load(mList.get(position)).into(imageView);

        //设置点击事件
        setupClickListener(imageView,position);

        return view;
    }

    /***
     * 点击事件
     * @param imageView
     * @param position
     */
    private void setupClickListener(ImageView imageView, final int position) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
                mActivity.overridePendingTransition(R.anim.finish_activity_back_in, R.anim.finish_activity_back_out);
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

}
