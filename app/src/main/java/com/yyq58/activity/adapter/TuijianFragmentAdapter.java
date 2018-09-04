package com.yyq58.activity.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class TuijianFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    public TuijianFragmentAdapter(Context context, List<String> list) {
        this.mList = list;
        this.mContext = context;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_tuijian, null);
            view.setTag(holder);
            holder.tvTitle =view.findViewById(R.id.tv_title);
            holder.tvPrivateChat =view.findViewById(R.id.tv_private_chat);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvTitle.setText(mList.get(i));

        ///私聊事件
        holder.tvPrivateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onEditClick(view,i);
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvTitle;
        TextView tvPrivateChat;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
