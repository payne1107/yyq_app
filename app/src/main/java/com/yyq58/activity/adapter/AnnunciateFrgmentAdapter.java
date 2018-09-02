package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.AnnuciteFragmentBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.utils.AutoUtils;


import java.util.List;

/****
 * 历史纪录---》通告
 */
public class AnnunciateFrgmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<AnnuciteFragmentBean.DataBean> mList;
    public AnnunciateFrgmentAdapter(Context context, List<AnnuciteFragmentBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<AnnuciteFragmentBean.DataBean> data) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_annunciate_list, null);
            view.setTag(holder);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            holder.tvPrice = view.findViewById(R.id.tv_price);
            holder.tvTime =view.findViewById(R.id.tv_time);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.tvCreateTime =view.findViewById(R.id.tv_create_time);
            holder.tvBrowserNum =view.findViewById(R.id.tv_browser_num);
            holder.tvEditNotice =view.findViewById(R.id.tv_edit_notice);
            holder.tvDeleNotice =view.findViewById(R.id.tv_dele_notice);
            holder.tvSetFull = view.findViewById(R.id.tv_set_full);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        AnnuciteFragmentBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String title =bean.getTitle();
            String price =bean.getPrice();
            String place = bean.getPlace();
            String time = bean.getTime();
            String createTime =bean.getCreateTime();
            int browsersNum = bean.getViews();
            holder.tvTitle.setText(StringUtils.isEmpty(title) ? "" : title);
            holder.tvPrice.setText(StringUtils.isEmpty(price) ? "" : price);
            holder.tvTime.setText(StringUtils.isEmpty(time) ? "" : time);
            holder.tvLocation.setText(StringUtils.isEmpty(place) ? "" : place);
            holder.tvCreateTime.setText(StringUtils.isEmpty(createTime) ? "" : createTime);
            holder.tvBrowserNum.setText("" + browsersNum);
        }

        holder.tvEditNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onEditClick(view, i);
            }
        });
        holder.tvDeleNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDeleClick(view, i);
            }
        });
        holder.tvSetFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onSaveClick(view, i);
            }
        });
        return view;
    }

    class  ViewHolder{
        TextView tvTitle;
        public TextView tvPrice;
        public TextView tvTime;
        public TextView tvLocation;
        public TextView tvCreateTime;
        public TextView tvBrowserNum;
        public TextView tvEditNotice;
        public TextView tvDeleNotice;
        public TextView tvSetFull;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
