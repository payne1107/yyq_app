package com.yyq58.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yyq58.R;
import com.yyq58.activity.adapter.QiangdanFragmentAdapter;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

/***
 * 抢单艺人列表
 */
public class QiangdanFragment extends BaseFragment{

    private MyListView listview;
    private List<String> mList = new ArrayList<>();
    private QiangdanFragmentAdapter adapter;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_qiangdan_list, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    private void setListener() {
        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {
                Toast.makeText(getActivity(),"siliao",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleClick(View view, int position) {

            }

            @Override
            public void onSaveClick(View view, int position) {
                Toast.makeText(getActivity(),"zhifu",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        listview = mRootView.findViewById(R.id.listview);
        mList.add("张三");
        mList.add("里斯");
        mList.add("payen");
        adapter = new QiangdanFragmentAdapter(getActivity(),mList);
        listview.setAdapter(adapter);
    }
}
