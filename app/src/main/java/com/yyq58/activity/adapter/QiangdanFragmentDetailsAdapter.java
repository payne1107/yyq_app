package com.yyq58.activity.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.RecommendNoticeBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IRecycleViewOnItemClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class QiangdanFragmentDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<RecommendNoticeBean.DataBean> mList;

    public QiangdanFragmentDetailsAdapter(Context context, List<RecommendNoticeBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    public void setData(List<RecommendNoticeBean.DataBean> data) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_qiangdan_details, null);
            view.setTag(holder);
            holder.ivAvatar =view.findViewById(R.id.iv_avatar);
            holder.tvTitle =view.findViewById(R.id.tv_title);
            holder.tvLableName =view.findViewById(R.id.tv_label_name);
            holder.tvPrice =view.findViewById(R.id.tv_price);
            holder.tvPrivateChat =view.findViewById(R.id.tv_private_chat);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        RecommendNoticeBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String avatarUrl = bean.getAVATAR();
            String title =bean.getTitle();
            String price =bean.getPrice();
            String category = bean.getTYPENAME();
            if (!StringUtils.isEmpty(avatarUrl)) {
                MyApplication.imageLoader.displayImage(avatarUrl, holder.ivAvatar);
            }
            holder.tvTitle.setText(StringUtils.isEmpty(title) ? "" : title);
            holder.tvPrice.setText(StringUtils.isEmpty(price) ? "" : price);
            holder.tvLableName.setText(StringUtils.isEmpty(category) ? "" : category);
        }
        //私聊
        holder.tvPrivateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, i);
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvTitle;
        public TextView tvPrivateChat;
        public CircleImageView ivAvatar;
        public TextView tvLableName;
        public TextView tvPrice;
    }

    private IRecycleViewOnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
