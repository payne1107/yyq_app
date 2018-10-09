package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.MineOrderForPayAndCompleteListBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class MineOrderDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineOrderForPayAndCompleteListBean.DataBean> mList;
    private int state ;

    public MineOrderDetailsAdapter(Context context, List<MineOrderForPayAndCompleteListBean.DataBean> list, int extraMineOrderTypeCode) {
        this.mContext = context;
        this.mList = list;
        state = extraMineOrderTypeCode;
    }

    public void setData(List<MineOrderForPayAndCompleteListBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_order_details, null);
            view.setTag(holder);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            holder.tvCancelOrder = view.findViewById(R.id.tv_cancel_order);
            holder.tvPay = view.findViewById(R.id.tv_pay);
            holder.tvContactYr =view.findViewById(R.id.tv_contact_yr);
            holder.tvLabelName =view.findViewById(R.id.tv_label_name);
            holder.tvPrice =view.findViewById(R.id.tv_price);
            holder.tvState = view.findViewById(R.id.tv_status);
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            holder.tvUserName =view.findViewById(R.id.tv_username);
            holder.tvCreateTime =view.findViewById(R.id.tv_date);
            holder.tvOrderNumer =view.findViewById(R.id.tv_order_num);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        MineOrderForPayAndCompleteListBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String title = bean.getTitle();
            String labelName =bean.getLabelName();
            double money = bean.getMoney();
            String avatarUrl = bean.getAvatar();
            String account = bean.getAccount();
            String createTime =bean.getCreateTime();
            String orderNum = bean.getId();

            holder.tvTitle.setText(StringUtils.isEmpty(title) ? "" : title);
            holder.tvLabelName.setText(StringUtils.isEmpty(labelName) ? "" : labelName);
            holder.tvPrice.setText(money <= 0 ? "面议" : "￥" + money);
            if (state == 1) {
                holder.tvState.setText("待定档");
            } else if(state ==2){
                holder.tvState.setText("待交易");
            } else if(state ==3){
                holder.tvState.setText("订单已完成");
                holder.tvPay.setVisibility(View.GONE);
                holder.tvCancelOrder.setText("订单已完成");
                holder.tvCancelOrder.setTextColor(mContext.getResources().getColor(R.color.color_62d));
                holder.tvCancelOrder.setEnabled(false);
            } else if(state ==4){
                holder.tvState.setText("退款中");
                holder.tvPay.setVisibility(View.GONE);
                holder.tvContactYr.setVisibility(View.GONE);
                holder.tvCancelOrder.setText("客服热线：18655755113 客服微信  ：w18655755113" );
                holder.tvCancelOrder.setTextColor(mContext.getResources().getColor(R.color.text_color_9));
                holder.tvCancelOrder.setEnabled(false);
            } else if(state ==5){
                holder.tvState.setText("待定档");
            } else if(state ==6){
                holder.tvState.setText("已定档");
                holder.tvPay.setVisibility(View.GONE);
                holder.tvCancelOrder.setVisibility(View.GONE);
            } else if(state ==7){
                holder.tvState.setText("待付款");
            } else {
                holder.tvState.setText("订单已完成");
                holder.tvPay.setVisibility(View.GONE);
                holder.tvContactYr.setVisibility(View.GONE);
                holder.tvCancelOrder.setText("客服热线：18655755113 客服微信  ：w18655755113" );
                holder.tvCancelOrder.setTextColor(mContext.getResources().getColor(R.color.text_color_9));
                holder.tvCancelOrder.setEnabled(false);
            }
            MyApplication.imageLoader.displayImage(avatarUrl, holder.ivAvatar);
            holder.tvUserName.setText(StringUtils.isEmpty(account) ? "" : account);
            holder.tvCreateTime.setText(StringUtils.isEmpty(createTime) ? "" : createTime);
            holder.tvOrderNumer.setText(StringUtils.isEmpty(orderNum) ? "" : orderNum);
        }

        holder.tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDeleClick(view, i);
            }
        });
        holder.tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onSaveClick(view,i);
            }
        });
        holder.tvContactYr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onEditClick(view,i);
            }
        });

        return view;
    }

    class ViewHolder {
        TextView tvTitle;
        public TextView tvCancelOrder;
        public TextView tvPay;
        public TextView tvContactYr;
        public TextView tvLabelName;
        public TextView tvPrice;
        public TextView tvState;
        public CircleImageView ivAvatar;
        public TextView tvUserName;
        public TextView tvCreateTime;
        public TextView tvOrderNumer;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
