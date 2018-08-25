package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;

import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.MineAttentionAdapter;
import com.yyq58.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/*我关注的人*/
public class MineAttentionActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private Context mContext;
    private List<String> mList = new ArrayList<>();


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_attention);
        mContext = MineAttentionActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的关注", true, true);
        listView = findViewById(R.id.listView);
        mList.add("YYYY");
        mList.add("XXXX");
        MineAttentionAdapter adapter = new MineAttentionAdapter(mContext,mList);
        listView.setAdapter(adapter);
    }
}
