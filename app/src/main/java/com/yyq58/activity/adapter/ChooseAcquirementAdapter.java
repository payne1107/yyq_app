package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.ChooseAcquirementActivity;
import com.yyq58.activity.bean.ChooseAcquirementBean;
import com.yyq58.activity.bean.MuHouBean;
import com.yyq58.activity.bean.TaiQianBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooseAcquirementAdapter extends BaseAdapter {
    private Context mContext;
    private List<ChooseAcquirementBean.DataBean> mList;
    private List<TaiQianBean> taiqianList = new ArrayList<>();
    private List<MuHouBean> muhouList = new ArrayList<>();
    public ChooseAcquirementAdapter(Context context, List<ChooseAcquirementBean.DataBean> list) {
        this.mContext = context;
        this.mList = list;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_acquirement, null);
            view.setTag(holder);
            holder.gridView1 = view.findViewById(R.id.gridview1);
            holder.gridView2 = view.findViewById(R.id.gridview1);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TaiQianBean bean ;
        MuHouBean bean1;
        for (int j = 0; j < mList.size(); j++) {
            //遍历数组 重新封装
            int type = mList.get(j).getLabelType();
            if (type == 1) {
                bean = new TaiQianBean();
                String labelId = mList.get(j).getLabelId();
                String labelName = mList.get(j).getLabelName();
                bean.setLabelId(labelId);
                bean.setLabelName(labelName);
                taiqianList.add(bean);
            } else {
                bean1 = new MuHouBean();
                String labelId = mList.get(j).getLabelId();
                String labelName = mList.get(j).getLabelName();
                bean1.setLabelId(labelId);
                bean1.setLabelName(labelName);
                muhouList.add(bean1);
            }
        }
        GridViewAdapter1 adapter1 = new GridViewAdapter1(mContext,taiqianList);
//        GridViewAdapter2 adapter2 = new GridViewAdapter2(mContext,muhouList);
        holder.gridView1.setAdapter(adapter1);
        return view;
    }

    class ViewHolder{
        GridView gridView1,gridView2;

    }
}
