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
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class MineAttentionAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineAttentionBean.DataBean> mList;
    public MineAttentionAdapter(Context context, List<MineAttentionBean.DataBean> list) {
        this.mList = list;
        this.mContext = context;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mine_attention, null);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.ivAvatar = view.findViewById(R.id.iv_avatar);
            holder.layoutCancelAttention  =view.findViewById(R.id.layout_cancel_attention);
            view.setTag(holder);
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
        //取消关注
        holder.layoutCancelAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDeleClick(view, i);
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvName;
        public TextView tvLocation;
        public CircleImageView ivAvatar;
        public AutoLinearLayout layoutCancelAttention;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
