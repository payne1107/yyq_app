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

public class ReleaseQiudanListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    public ReleaseQiudanListAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<String> data) {
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_release_qiudan, null);
            view.setTag(holder);
            holder.tvLabelName = view.findViewById(R.id.tv_label_name);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvLabelName.setText(mList.get(position));

        return view;
    }

    class ViewHolder{
        TextView tvLabelName;
    }
}
