package com.yyq58.activity.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.widget.IButtonClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditNoticeListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    //存储人数
    private Map<Integer,String> mlistPersonCouont;

    public Map<Integer,String> getMlistPersonCouont() {
        return mlistPersonCouont;
    }

    public EditNoticeListAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;

    }

    /***
     * 更新数据
     * @param data
     */
    public void setData(List<String> data) {
        mlistPersonCouont = new HashMap<>();
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                mlistPersonCouont.put(i, "1");
            }
        }
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_edit_notice_list, null);
            view.setTag(holder);
            holder.tvLabelName = view.findViewById(R.id.tv_label_name);
            holder.tvDeleteNotice = view.findViewById(R.id.tv_delete_notice);
            holder.etPersonNum = view.findViewById(R.id.et_person_num);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvLabelName.setText(mList.get(position));

        holder.tvDeleteNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDeleClick(view, position);
            }
        });

        holder.etPersonNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mlistPersonCouont.put(position, editable.toString());
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvLabelName;
        TextView tvDeleteNotice;
        EditText etPersonNum;
    }

    private IButtonClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(IButtonClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
