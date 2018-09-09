package com.yyq58.activity.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.MineContactsActivity;
import com.yyq58.activity.base.BaseFragment;

public class Fragment3 extends BaseFragment implements View.OnClickListener {

    private TextView tvSet;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment3, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        setInVisibleTitleIcon("消息列表", true, false);
        tvSet = mRootView.findViewById(R.id.activity_set);
        tvSet.setVisibility(View.VISIBLE);
        Drawable drawable1 = getResources().getDrawable(R.mipmap.tabbar_usercenter);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        tvSet.setCompoundDrawables(null,drawable1 ,null,null);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                startActivity(new Intent(getActivity(), MineContactsActivity.class));
                break;
        }
    }
}
