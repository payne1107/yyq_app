package com.yyq58.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.yyq58.R;
import com.yyq58.activity.bean.MuHouBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridViewAdapter4 extends BaseAdapter {
    private Context mContext;
    private List<MuHouBean> mList;
    //记录选中的状态
    private static HashMap<Integer,Boolean> mapSelect;
    //标签id
    private static List<String> listCategory;
    private static List<String> listCategoryName;
    public static List<String> getListCategoryName() {
        return listCategoryName;
    }
    public static List<String> getListCategory() {
        return listCategory;
    }
    private static List<String> listCategoryType;
    public static List<String> getListCategoryType() {
        return listCategoryType;
    }
    public GridViewAdapter4(Context context, List<MuHouBean> list) {
        this.mContext = context;
        this.mList = list;
        mapSelect = new HashMap<>();
        listCategory = new ArrayList<>();
        listCategoryName = new ArrayList<>();
        listCategoryType = new ArrayList<>();
        initData();
    }

    private void initData() {
        for (int i = 0; i < mList.size(); i++) {
            mapSelect.put(i, false);
        }
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_muhou_1, null);
            view.setTag(holder);
            holder.tvName = view.findViewById(R.id.tv_name);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(position).getLabelName());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapSelect.get(position)) {
                    mapSelect.put(position, false);
                    if (listCategory != null) {
                        for (int i = 0; i < listCategory.size(); i++) {
                            if (mList.get(position).getLabelId() == listCategory.get(i)) {
                                listCategory.remove(i);
                                listCategoryName.remove(i);
                                listCategoryType.remove(i);
                            }
                        }
                    }
                } else {
                    mapSelect.put(position, true);
                    //添加用户选择的userid
                    listCategory.add(mList.get(position).getLabelId());
                    listCategoryName.add(mList.get(position).getLabelName());
                    listCategoryType.add(String.valueOf(mList.get(position).getLabelType()));
                }
            }
        });
        return view;
    }

    class ViewHolder{
        CheckBox tvName;
    }
}
