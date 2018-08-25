package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.adapter.GridViewAdapter1;
import com.yyq58.activity.adapter.GridViewAdapter2;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.ChooseAcquirementBean;
import com.yyq58.activity.bean.MuHouBean;
import com.yyq58.activity.bean.TaiQianBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * 选择才艺
 */
public class ChooseAcquirementActivity extends BaseActivity {

    private List<ChooseAcquirementBean.DataBean> mList;
    private Context mContext;
    private GridView gridView1,gridView2;
    private List<TaiQianBean> taiqianList = new ArrayList<>();
    private List<MuHouBean> muhouList = new ArrayList<>();
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
        setInVisibleTitleIcon("选择才艺", true, true);
        gridView1 = findViewById(R.id.gridview1);
        gridView2 = findViewById(R.id.gridview2);
        queryMineAcquirement();


        setListener();
    }

    private void setListener() {
        final Intent intent = new Intent();
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MuHouBean bean = (MuHouBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String labelId = bean.getLabelId();
                    String labelName = bean.getLabelName();

                    intent.putExtra("laeblId", labelId);
                    intent.putExtra("labelName", labelName);
                    setResult(RegisterActivity.REQUEST_CHOOSE_QCQUIREMENT_CODE, intent);
                    finish();
                }
            }
        });

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaiQianBean bean = (TaiQianBean) adapterView.getAdapter().getItem(i);
                if (bean != null) {
                    String labelId = bean.getLabelId();
                    String labelName = bean.getLabelName();


                    intent.putExtra("laeblId", labelId);
                    intent.putExtra("labelName", labelName);
                    setResult(RegisterActivity.REQUEST_CHOOSE_QCQUIREMENT_CODE, intent);
                    finish();
                }
            }
        });
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
        ChooseAcquirementBean bean = JSON.parseObject(json, ChooseAcquirementBean.class);
        if (bean != null) {
            mList = bean.getData();
            TaiQianBean taiQianBean ;
            MuHouBean muHouBean;
            for (int j = 0; j < mList.size(); j++) {
                //遍历数组 重新封装
                int type = mList.get(j).getLabelType();
                if (type == 1) {
                    taiQianBean = new TaiQianBean();
                    String labelId = mList.get(j).getLabelId();
                    String labelName = mList.get(j).getLabelName();
                    taiQianBean.setLabelId(labelId);
                    taiQianBean.setLabelName(labelName);
                    taiqianList.add(taiQianBean);
                } else {
                    muHouBean = new MuHouBean();
                    String labelId = mList.get(j).getLabelId();
                    String labelName = mList.get(j).getLabelName();
                    muHouBean.setLabelId(labelId);
                    muHouBean.setLabelName(labelName);
                    muhouList.add(muHouBean);
                }
            }
            GridViewAdapter1 adapter1 = new GridViewAdapter1(mContext, taiqianList);
            GridViewAdapter2 adapter2 = new GridViewAdapter2(mContext, muhouList);
            gridView1.setAdapter(adapter1);
            gridView2.setAdapter(adapter2);
        }
    }
}
