package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.ScheduleListBean;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class ScheduleManagementAdapter extends BaseAdapter {
    private Context mContext;
    private List<ScheduleListBean.DataBean> mList;
    public ScheduleManagementAdapter(Context context, List<ScheduleListBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    public void setData(List<ScheduleListBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_schedule_managemet, null);
            view.setTag(holder);
            holder.tvLinkman = view.findViewById(R.id.tv_linkman);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.tvDate = view.findViewById(R.id.tv_date);

            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ScheduleListBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String detailsTime =bean.getDetailTime();
            String place = bean.getPlace();
            String linkman = bean.getLinkMan();
            holder.tvLinkman.setText(StringUtils.isEmpty(linkman) ? "" : linkman);
            holder.tvLocation.setText(StringUtils.isEmpty(place) ? "" : place);
            holder.tvDate.setText(StringUtils.isEmpty(detailsTime) ? "" : detailsTime);
        }
        return view;
    }

    class ViewHolder{
        TextView tvDate;
        TextView tvLinkman;
        TextView tvLocation;
        TextView tvEdit;
        TextView tvDelete;
    }
}
