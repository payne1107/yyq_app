package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.AnnuciteFragmentBean;
import com.yyq58.activity.bean.VipListBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class VipListAdapter extends BaseAdapter {
    private Context mContext;
    private List<VipListBean.DataBean> mList;

    public VipListAdapter(Context context, List<VipListBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<VipListBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_vip_list, null);
            view.setTag(holder);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            holder.tvPullCount = view.findViewById(R.id.tv_pull_count);
            holder.tvPrice = view.findViewById(R.id.tv_price);
            holder.tvDays=view.findViewById(R.id.tv_days);
            holder.tvPullCount2=view.findViewById(R.id.tv_pull_count2);
            holder.tvDays2= view.findViewById(R.id.tv_days2);
            holder.tvVipDes = view.findViewById(R.id.tv_vip_des);
            holder.layoutOpenVip = view.findViewById(R.id.layout_open_vip);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        VipListBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String days = bean.getDay();
            String desc = bean.getDescribed();
            final double money = bean.getMoney();
            String pullNum = bean.getPull_num();
            String name = bean.getName();
            if (!StringUtils.isEmpty(days)) {
                holder.tvDays.setText(days + "天");
                holder.tvDays2.setText(days + "天");
            }
            if (!StringUtils.isEmpty(pullNum)) {
                holder.tvPullCount.setText(pullNum + "次");
                holder.tvPullCount2.setText(pullNum + "次");
            }
            holder.tvVipDes.setText(StringUtils.isEmpty(desc) ? "" : desc);
            holder.tvPrice.setText("￥" + money);
            holder.tvTitle.setText(StringUtils.isEmpty(name) ? "" : name);
            holder.layoutOpenVip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onEditClick(view, i);
                }
            });
        }
        return view;
    }

    class ViewHolder{
        TextView tvTitle,tvPullCount,tvPrice,tvDays,tvPullCount2,tvDays2,tvVipDes;
        public AutoLinearLayout layoutOpenVip;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
