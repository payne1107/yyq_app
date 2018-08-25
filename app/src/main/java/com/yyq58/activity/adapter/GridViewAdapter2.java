package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.MuHouBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class GridViewAdapter2 extends BaseAdapter {
    private Context mContext;
    private List<MuHouBean> mList;

    public GridViewAdapter2(Context context, List<MuHouBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i) ;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_muhou, null);
            view.setTag(holder);
            holder.tvName = view.findViewById(R.id.tv_name);

            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(i).getLabelName());
        return view;
    }

    class ViewHolder{
        TextView tvName;
    }
}
