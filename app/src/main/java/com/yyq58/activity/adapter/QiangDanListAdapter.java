package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.Appv1NoticeBean;
import com.yyq58.activity.bean.QiangDanFramgmentBean;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class QiangDanListAdapter extends BaseAdapter {
    private Context mContext;
    private List<QiangDanFramgmentBean.DataBean> mList;

    public QiangDanListAdapter(Context context, List<QiangDanFramgmentBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    public void setData(List<QiangDanFramgmentBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_qiangdan_list, null);
            view.setTag(holder);
            holder.tvTitle=view.findViewById(R.id.tv_title);
            holder.tvPrice = view.findViewById(R.id.tv_price);
            holder.tvTime =view.findViewById(R.id.tv_time);
            holder.tvLocation =view.findViewById(R.id.tv_location);
            holder.tvStatus =view.findViewById(R.id.tv_status);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        QiangDanFramgmentBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String title = bean.getTitle();
            String price =bean.getPrice();
            String time =bean.getTime();
            String place =bean.getPlace();
            int status = bean.getStatus();
            holder.tvTime.setText(StringUtils.isEmpty(time) ? "" : time);
            holder.tvTitle.setText(StringUtils.isEmpty(title) ? "" : title);
            holder.tvLocation.setText(StringUtils.isEmpty(place) ? "" : place);
            holder.tvPrice.setText(StringUtils.isEmpty(price) ? "" : price);
            if (status == 1) {
                holder.tvStatus.setText("订单待完成");
            } else {
                holder.tvStatus.setText("订单已完成");
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvTitle;
        public TextView tvPrice;
        public TextView tvTime;
        public TextView tvLocation;
        public TextView tvStatus;
    }
}
