package com.yyq58.activity.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.MineFansListBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class MineFansAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineFansListBean.DataBean> mList;

    public MineFansAdapter(Context context, List<MineFansListBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MineFansListBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_fans, null);
            view.setTag(holder);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            holder.layoutCancelAttention  =view.findViewById(R.id.layout_cancel_attention);
            holder.tvAttention = view.findViewById(R.id.tv_attention);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MineFansListBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String account = bean.getAccount();
            String province =bean.getProvince();
            String city  = bean.getCity();
            String avatar = bean.getAvatar();
            boolean isFollow = bean.isIsFollow();//true已经关注
            holder.tvName.setText(StringUtils.isEmpty(account) ? "" : account);
            holder.tvLocation.setText(province + " " + city);
            if (!StringUtils.isEmpty(avatar)) {
                MyApplication.imageLoader.displayImage(avatar, holder.ivAvatar);
            }
            if (isFollow) {
                //已经关注
                holder.tvAttention.setText("已关注");
                holder.tvAttention.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.layoutCancelAttention.setBackgroundResource(R.drawable.guanzhu_btn_style);
                Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.icon_yiguanzhu);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                holder.tvAttention.setCompoundDrawables(drawable1, null, null, null);
            } else {
                holder.tvAttention.setText("加关注");
                Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.icon_jiaguanzhu_white);
                drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                holder.tvAttention.setCompoundDrawables(drawable1, null, null, null);
                holder.tvAttention.setTextColor(mContext.getResources().getColor(R.color.color_4b3a75));
                holder.layoutCancelAttention.setBackgroundResource(R.drawable.cancel_guanzhu_btn_style);
            }
        }
        //取消关注
        holder.layoutCancelAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDeleClick(view, i);
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvName;
        public TextView tvLocation;
        public CircleImageView ivAvatar;
        public AutoLinearLayout layoutCancelAttention;
        public TextView tvAttention;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
