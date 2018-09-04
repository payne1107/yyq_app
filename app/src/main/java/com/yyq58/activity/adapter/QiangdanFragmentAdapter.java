package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.Appv1NoticeBean;
import com.yyq58.activity.bean.QiangDanFragmentBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class QiangdanFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<QiangDanFragmentBean.DataBean> mList;

    public QiangdanFragmentAdapter(Context context, List<QiangDanFragmentBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    public void setData(List<QiangDanFragmentBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_qiangdan, null);
            view.setTag(holder);
            holder.tvTitle =view.findViewById(R.id.tv_title);
            holder.tvLocation =view.findViewById(R.id.tv_location);
            holder.ivAvatar=view.findViewById(R.id.iv_avatar);
            holder.tvPayOrder = view.findViewById(R.id.tv_pay_order);
            holder.tvPrivateChat = view.findViewById(R.id.tv_private_chat);
            AutoUtils.autoSize(view);
        } else {
            holder= (ViewHolder) view.getTag();
        }
        QiangDanFragmentBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String account = bean.getAccount();
            String avatarUrl = bean.getAVATAR();
            String province =bean.getProvince();
            String city = bean.getCity();
            if (!StringUtils.isEmpty(avatarUrl)) {
                MyApplication.imageLoader.displayImage(avatarUrl, holder.ivAvatar);
            }
            holder.tvTitle.setText(StringUtils.isEmpty(account) ? "" : account);
            holder.tvLocation.setText(province + " " + city);
        }
        holder.tvPayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onSaveClick(view,i);
            }
        });

        holder.tvPrivateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onEditClick(view,i);
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvTitle,tvLocation;
        public TextView tvPrivateChat ,tvPayOrder;
        CircleImageView ivAvatar;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
