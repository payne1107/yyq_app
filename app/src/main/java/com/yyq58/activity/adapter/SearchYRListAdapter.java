package com.yyq58.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yyq58.R;
import com.yyq58.activity.bean.SearchYRListBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.IRecycleViewOnItemClickListener;

import java.util.List;

public class SearchYRListAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<SearchYRListBean.DataBean> mList;
    public SearchYRListAdapter(Context context, List<SearchYRListBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<SearchYRListBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(mContext).inflate(R.layout.item_search_yr_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.userAvatar.getLayoutParams().height = (position % 2) * 200 + 600; //偶数和奇数的图片设置不同的高度，以到达错开的目的
        SearchYRListBean.DataBean bean = mList.get(position);
        if (bean != null) {
            String accountName = bean.getACCOUNT();
            holder1.userName.setText(StringUtils.isEmpty(accountName) ? "" : accountName);
            SearchYRListBean.DataBean.AVATARBean avatarBean = bean.getAVATAR();
            if (bean != null) {
                String avatarUrl = avatarBean.getUrl();
                Glide.with(mContext).load(avatarUrl).into(holder1.userAvatar);
            }
            holder1.userAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ImageView userAvatar;
        private TextView userName;
        public ViewHolder(View itemView) {
            super(itemView);
             userAvatar =  itemView.findViewById(R.id.user_avatar);
             userName =  itemView.findViewById(R.id.user_name);
        }
    }

    private IRecycleViewOnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
