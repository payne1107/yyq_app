package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.MineAttentionBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class MineContactsAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineAttentionBean.DataBean> mList;

    public MineContactsAdapter(Context context, List<MineAttentionBean.DataBean> list) {
        mContext = context;
        this.mList = list;
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<MineAttentionBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_contacts, null);
            view.setTag(holder);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MineAttentionBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String account = bean.getAccount();
            String province =bean.getProvince();
            String city  = bean.getCity();
            String avatar = bean.getAvatar();
            holder.tvName.setText(StringUtils.isEmpty(account) ? "" : account);
            holder.tvLocation.setText(province + " " + city);
            if (!StringUtils.isEmpty(avatar)) {
                MyApplication.imageLoader.displayImage(avatar, holder.ivAvatar);
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvName;
        public TextView tvLocation;
        public CircleImageView ivAvatar;
    }
}
