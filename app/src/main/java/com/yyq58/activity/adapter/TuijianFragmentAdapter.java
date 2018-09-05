package com.yyq58.activity.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.TuijianFragmentListBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class TuijianFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<TuijianFragmentListBean.DataBean> mList;

    public TuijianFragmentAdapter(Context context, List<TuijianFragmentListBean.DataBean> list) {
        this.mList = list;
        this.mContext = context;
    }

    public void setData(List<TuijianFragmentListBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_tuijian, null);
            view.setTag(holder);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            holder.tvPrivateChat = view.findViewById(R.id.tv_private_chat);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TuijianFragmentListBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String account = bean.getACCOUNT();
            String province =bean.getPROVINCE();
            String city = bean.getCITY();
            String avatarUrl =bean.getAVATAR();
            holder.tvLocation.setText(province + " " + city);
            holder.tvTitle.setText(StringUtils.isEmpty(account) ? "" : account);
            if (!StringUtils.isEmpty(avatarUrl)) {
                MyApplication.imageLoader.displayImage(avatarUrl, holder.ivAvatar);
            }
        }

        ///私聊事件
        holder.tvPrivateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onEditClick(view, i);
            }
        });
        return view;
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvPrivateChat;
        public TextView tvLocation;
        public CircleImageView ivAvatar;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
