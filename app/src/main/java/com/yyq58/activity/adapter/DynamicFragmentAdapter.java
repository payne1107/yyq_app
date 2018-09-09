package com.yyq58.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.bean.DynamicFragmentBean;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.SpacesItemDecoration;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/***
 * 动态
 */
public class DynamicFragmentAdapter extends BaseAdapter{
    private Context mContext;
    private List<DynamicFragmentBean.DataBean> mList;
    public DynamicFragmentAdapter(Context activity, List<DynamicFragmentBean.DataBean> list) {
        this.mContext = activity;
        this.mList = list;
    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<DynamicFragmentBean.DataBean> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_fragment, null);
            holder.tvTime = view.findViewById(R.id.tv_time);
            holder.tvContent =view.findViewById(R.id.tv_content);
            holder.recycleview =view.findViewById(R.id.sub_recycleview);
            holder.tvDeleteDynamic = view.findViewById(R.id.tv_delete_dynamic);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        DynamicFragmentBean.DataBean bean = mList.get(i);
        if (bean != null) {
            String time =bean.getCreate_time();
            String content =bean.getContent();
            List<DynamicFragmentBean.DataBean.UrlBean> urlList = bean.getUrl();
            holder.tvTime.setText(StringUtils.isEmpty(time) ? "" : time);
            holder.tvContent.setText(StringUtils.isEmpty(content) ? "" : content);
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
            holder.recycleview.addItemDecoration(new SpacesItemDecoration(2));
            holder.recycleview.setLayoutManager(layoutManager);
            SubRecycleViewAdadpter adadpter = new SubRecycleViewAdadpter(mContext,urlList);
            holder.recycleview.setAdapter(adadpter);
        }
        return view;
    }

    class ViewHolder{
        TextView tvTime;
        public RecyclerView recycleview;
        TextView tvContent;
        TextView tvDeleteDynamic;
    }

    /***
     * Recycleview适配器
     */
    private class SubRecycleViewAdadpter extends RecyclerView.Adapter{
        private Context mContext;
        private List<DynamicFragmentBean.DataBean.UrlBean> mUrlList;

        public SubRecycleViewAdadpter(Context context, List<DynamicFragmentBean.DataBean.UrlBean> urlList) {
            this.mUrlList = urlList;
            this.mContext = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_pic, null));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            DynamicFragmentBean.DataBean.UrlBean bean = mUrlList.get(position);
            if (bean != null) {
                String urlImg = bean.getUrl();
                MyApplication.imageLoader.displayImage(urlImg,viewHolder.ivDynamicPic);
            }
        }

        @Override
        public int getItemCount() {
            return mUrlList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivDynamicPic;
            public ViewHolder(View itemView) {
                super(itemView);
                ivDynamicPic = itemView.findViewById(R.id.iv_dynaimc_pc);
            }
        }
    }
}
