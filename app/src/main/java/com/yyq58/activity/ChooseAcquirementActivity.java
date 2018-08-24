package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.adapter.ChooseAcquirementAdapter;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.ChooseAcquirementBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * 选择才艺
 */
public class ChooseAcquirementActivity extends BaseActivity {

    private List<ChooseAcquirementBean.DataBean> mList;
    private Context mContext;
    private ListView listView;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_acquirement);
        mContext = ChooseAcquirementActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        listView = findViewById(R.id.listView);
        queryMineAcquirement();
    }

    /****
     * 获取我的才艺列表
     */
    private void queryMineAcquirement() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "1");
        httpPostRequest(ConfigUtil.QUERY_MINE_ACQUIREMENT_URL, params, ConfigUtil.QUERY_MINE_ACQUIREMENT_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MINE_ACQUIREMENT_URL_ACTION:
                handlerQueryMineAcquirement(json);
                break;
        }
    }

    /***
     * 处理我的才艺列表数据 台前
     * @param json
     */
    private void handlerQueryMineAcquirement(String json) {
        Log.d("Dong", "-----我的才艺--------->" + json);
        ChooseAcquirementBean bean = JSON.parseObject(json, ChooseAcquirementBean.class);
        if (bean != null) {
            mList = bean.getData();
            ChooseAcquirementAdapter adapter = new ChooseAcquirementAdapter(mContext, mList);
            listView.setAdapter(adapter);
        }
    }
}
