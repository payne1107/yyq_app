package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.MineOrderDetailsAdapter;
import com.yyq58.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.yyq58.activity.MineOrderActivity.EXTRA_MINE_ORDER_TITLE_NAME;

public class MineOrderDetailsActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private MineOrderDetailsAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.layout_mine_order_details);
        mContext = MineOrderDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        String extraTitleName = getIntent().getStringExtra(EXTRA_MINE_ORDER_TITLE_NAME);
        setInVisibleTitleIcon(extraTitleName, true, true);

        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);


        mList.add("test1");
        mList.add("test1");
        mList.add("test1");
        adapter = new MineOrderDetailsAdapter(mContext,mList);
        listView.setAdapter(adapter);
    }
}
