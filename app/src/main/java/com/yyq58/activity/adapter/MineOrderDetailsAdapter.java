package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class MineOrderDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public MineOrderDetailsAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_order_details, null);
            view.setTag(holder);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvTitle.setText(mList.get(i));
        return view;
    }

    class ViewHolder{
        TextView tvTitle;
    }
}
