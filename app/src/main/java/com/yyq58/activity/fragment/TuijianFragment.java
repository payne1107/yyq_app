package com.yyq58.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yyq58.R;
import com.yyq58.activity.adapter.TuijianFragmentAdapter;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

/***
 * 推荐艺人列表
 */
public class TuijianFragment extends BaseFragment{

    private MyListView listView;
    private List<String> mList = new ArrayList<>();
    private TuijianFragmentAdapter adapter;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_tuijian_list, null);
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
                //私聊事件
                Toast.makeText(getActivity(), "siliao", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleClick(View view, int position) {

            }

            @Override
            public void onSaveClick(View view, int position) {

            }
        });
    }

    private void initView() {
        listView = mRootView.findViewById(R.id.listview);
        mList.add("不忘初心");
        mList.add("方得始终");
        mList.add("励志前行");
        mList.add("不忘初心");
        mList.add("方得始终");
        mList.add("励志前行");
        adapter = new TuijianFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);


    }
}
