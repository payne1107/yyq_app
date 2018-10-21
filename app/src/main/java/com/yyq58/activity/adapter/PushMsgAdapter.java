package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.bean.PushMsgListBean;
import com.yyq58.activity.bean.SystemNoticeBean;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class PushMsgAdapter extends BaseAdapter {
    private Context mContext;
    private List<PushMsgListBean.DataBean> mList;

    public PushMsgAdapter(Context context, List<PushMsgListBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<PushMsgListBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_push_msg,null);
            holder.tvNoticeContent = view.findViewById(R.id.tv_notice_content);
            holder.tvTime = view.findViewById(R.id.tv_time);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        PushMsgListBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String createTime = bean.getCreateTime();
            String content = bean.getContent();
            holder.tvTime.setText(StringUtils.isEmpty(createTime) ? "" : createTime);
            holder.tvNoticeContent.setText(StringUtils.isEmpty(content) ? "" : content);
        }
        return view;
    }

    class ViewHolder{
        TextView tvTime;
        public TextView tvNoticeContent;
    }
}
