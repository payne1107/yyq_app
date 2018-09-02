package com.yyq58.activity.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.Appv1NoticeBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.IRecycleViewOnItemClickListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class NewestFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<Appv1NoticeBean.DataBean> mList;

    public NewestFragmentAdapter(Context activity, List<Appv1NoticeBean.DataBean> list) {
        this.mContext = activity;
        this.mList = list;
    }

    public void setData(List<Appv1NoticeBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_newest_fragment, null);
            view.setTag(holder);
            holder.tvTitle = view.findViewById(R.id.tv_title);
            holder.tvTime = view.findViewById(R.id.tv_time);
            holder.tvTime2 = view.findViewById(R.id.tv_time2);
            holder.tvPrice = view.findViewById(R.id.tv_price);
            holder.tvBrowserNum = view.findViewById(R.id.tv_browser_num);
            holder.tvLocation = view.findViewById(R.id.tv_location);
            holder.layoutIsQiangDan =view.findViewById(R.id.layout_is_qiangdan);
            holder.ivGuoQi = view.findViewById(R.id.iv_guoqi);
            AutoUtils.autoSize(view);
        } else {
           holder = (ViewHolder) view.getTag();
        }
        Appv1NoticeBean.DataBean bean = mList.get(i);
        if (bean !=null) {
            String title = bean.getTitle();
            String price = bean.getPrice();
            String location = bean.getPlace();
            int browserNum = bean.getViews();
            String time = bean.getTime();
            String createTime = bean.getCreateTime();
            boolean isdeadTime = bean.isDeadTime();

            if (isdeadTime) {
                holder.ivGuoQi.setVisibility(View.VISIBLE);
            } else {
                holder.ivGuoQi.setVisibility(View.GONE);
            }
            holder.tvBrowserNum.setText("" + browserNum);
            holder.tvLocation.setText(StringUtils.isEmpty(location) ? "" : location);
            holder.tvPrice.setText(StringUtils.isEmpty(price) ? "" : price);
            holder.tvTime.setText(StringUtils.isEmpty(time) ? "" : time);
            holder.tvTime2.setText(StringUtils.isEmpty(createTime) ? "" : createTime);
            holder.tvTitle.setText(StringUtils.isEmpty(title) ? "" : title);

            holder.layoutIsQiangDan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, i);
                }
            });
        }
        return view;
    }

    class ViewHolder{
        TextView tvTitle;
        public TextView tvTime;
        public TextView tvTime2;
        public TextView tvPrice;
        public TextView tvBrowserNum;
        public TextView tvLocation;
        public AutoLinearLayout layoutIsQiangDan;
        public ImageView ivGuoQi;
    }

    private IRecycleViewOnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IRecycleViewOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
