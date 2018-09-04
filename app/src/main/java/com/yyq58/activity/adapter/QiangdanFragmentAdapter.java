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

public class QiangdanFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public QiangdanFragmentAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_qiangdan, null);
            view.setTag(holder);
            holder.tvTitle =view.findViewById(R.id.tv_title);
            holder.tvPayOrder = view.findViewById(R.id.tv_pay_order);
            holder.tvPrivateChat = view.findViewById(R.id.tv_private_chat);
            AutoUtils.autoSize(view);
        } else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tvTitle.setText(mList.get(i));

        holder.tvPayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onSaveClick(view,i);
            }
        });

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
        public TextView tvPrivateChat ,tvPayOrder;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
