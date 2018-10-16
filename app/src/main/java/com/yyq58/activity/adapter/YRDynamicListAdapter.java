package com.yyq58.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.CheckBigImgActivity;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.DynamicFragmentBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.SpacesItemDecoration;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

public class YRDynamicListAdapter extends BaseAdapter {
    private Context mContext;
    private List<DynamicFragmentBean.DataBean> mList;

    public YRDynamicListAdapter(Context context, List<DynamicFragmentBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<DynamicFragmentBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_yr_dynamic_list, null);
            view.setTag(holder);
            holder.tvContent =view.findViewById(R.id.tv_content);
            holder.recycleview =view.findViewById(R.id.sub_recycleview);
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.tvName = view.findViewById(R.id.tv_username);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        DynamicFragmentBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String content =bean.getContent();
            String avatarUrl = bean.getAvatar();
            String accountName = bean.getAccount();
            holder.tvName.setText(StringUtils.isEmpty(accountName) ? "" : accountName);
            List<DynamicFragmentBean.DataBean.UrlBean> urlList = bean.getUrl();
            holder.tvContent.setText(StringUtils.isEmpty(content) ? "" : content);
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
            holder.recycleview.addItemDecoration(new SpacesItemDecoration(2));
            holder.recycleview.setLayoutManager(layoutManager);
            SubRecycleViewAdadpter adadpter = new SubRecycleViewAdadpter(mContext,urlList);
            holder.recycleview.setAdapter(adadpter);
            if (!StringUtils.isEmpty(avatarUrl)) {
                MyApplication.imageLoader.displayImage(avatarUrl, holder.ivAvatar);
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvName;
        public RecyclerView recycleview;
        TextView tvContent;
        TextView tvLocation;
        CircleImageView ivAvatar;
    }

    /***
     * Recycleview适配器
     */
    private class SubRecycleViewAdadpter extends RecyclerView.Adapter{
        private Context mContext;
        private List<DynamicFragmentBean.DataBean.UrlBean> mUrlList;

        public SubRecycleViewAdadpter(Context context, List<DynamicFragmentBean.DataBean.UrlBean> urlList) {
            this.mUrlList = urlList;
            this.mContext = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SubRecycleViewAdadpter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_pic, null));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            SubRecycleViewAdadpter.ViewHolder viewHolder = (SubRecycleViewAdadpter.ViewHolder) holder;
            DynamicFragmentBean.DataBean.UrlBean bean = mUrlList.get(position);
            if (bean != null) {
                String urlImg = bean.getUrl();
                MyApplication.imageLoader.displayImage(urlImg,viewHolder.ivDynamicPic);
                ((ViewHolder) holder).ivDynamicPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> listUrl = new ArrayList<>();
                        for (int i=0;i<mUrlList.size();i++) {
                            listUrl.add(mUrlList.get(i).getUrl());
                        }
                        //把图片集合转成json传递到下个页面
                        String jsonImgs = JSON.toJSONString(listUrl);
                        Intent intent = new Intent(mContext, CheckBigImgActivity.class);
                        intent.putExtra("jsonImgs", jsonImgs);
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mUrlList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivDynamicPic;
            public ViewHolder(View itemView) {
                super(itemView);
                ivDynamicPic = itemView.findViewById(R.id.iv_dynaimc_pc);
            }
        }
    }
}
