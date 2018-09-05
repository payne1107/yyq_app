package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.QiuDanFragmentListbean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class QiuDanFragmentAdapter extends BaseAdapter{

    private Context mContext;
    private List<QiuDanFragmentListbean.DataBean> mList;

    public QiuDanFragmentAdapter(Context context, List<QiuDanFragmentListbean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<QiuDanFragmentListbean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_qiudan, null);
            view.setTag(holder);
            holder.tvTitle =view.findViewById(R.id.tv_title);
            holder.tvEditNotice =view.findViewById(R.id.tv_edit_notice);
            holder.tvDeleNotice =view.findViewById(R.id.tv_dele_notice);
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            holder.tvLocation =view.findViewById(R.id.tv_location);
            holder.tvTime =view.findViewById(R.id.tv_time);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        QiuDanFragmentListbean.DataBean bean = mList.get(i);
        if (bean != null) {
            String account =bean.getAccount();
            String avatarUrl =bean.getAvatar();
            String time = bean.getTime();
            String place = bean.getPlace();
            holder.tvTitle.setText(StringUtils.isEmpty(account) ? "" : account);
            if (!StringUtils.isEmpty(avatarUrl)) {
                MyApplication.imageLoader.displayImage(avatarUrl, holder.ivAvatar);
            }
            holder.tvLocation.setText(StringUtils.isEmpty(place) ? "" : place);
            holder.tvTime.setText(StringUtils.isEmpty(time) ? "" : time);
        }

        holder.tvEditNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onEditClick(view, i);
            }
        });
        holder.tvDeleNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDeleClick(view, i);
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvTitle,tvEditNotice,tvDeleNotice;
        CircleImageView ivAvatar;
        public TextView tvLocation;
        public TextView tvTime;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
