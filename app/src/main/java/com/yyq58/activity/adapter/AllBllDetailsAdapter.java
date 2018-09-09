package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.AllBillDetailsBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class AllBllDetailsAdapter extends BaseAdapter{
    private Context mContext;
    private List<AllBillDetailsBean.DataBean> mList;

    public AllBllDetailsAdapter(Context context, List<AllBillDetailsBean.DataBean> list) {
        mContext = context;
        mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<AllBillDetailsBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_all_bill_details, null);
            view.setTag(holder);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            holder.tvTime = view.findViewById(R.id.tv_time);
            holder.tvMoney=view.findViewById(R.id.tv_money);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        AllBillDetailsBean.DataBean bean = mList.get(i);
        if (bean != null) {
            int fromType = bean.getFromtype();
            if (fromType == 1) {
                holder.tvTitle.setText("充值");
            } else if (fromType == 2) {
                holder.tvTitle.setText("发红包");
            } else if (fromType == 3) {
                holder.tvTitle.setText("提现");
            } else if (fromType ==4) {
                holder.tvTitle.setText("抢红包");
            } else if (fromType == 5) {
                holder.tvTitle.setText("删除通告返回红包");
            } else if (fromType ==6) {
                holder.tvTitle.setText("分享奖励");
            } else if (fromType == 7) {
                holder.tvTitle.setText("购买套餐");
            } else if (fromType == 8) {
            } else if (fromType == 9) {
                holder.tvTitle.setText("通告交易");
            }
            holder.tvTime.setText(bean.getCreatetime());
            int type = bean.getType();
            double money = bean.getMoney();
            if (type == 1) {
                holder.tvMoney.setText("+" + money);
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.google_red));
            } else {
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.color_62d));
                holder.tvMoney.setText("-" + money);
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvTitle;
        public TextView tvMoney ,tvTime;
    }
}
