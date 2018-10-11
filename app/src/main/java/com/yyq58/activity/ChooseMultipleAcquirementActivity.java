package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.adapter.GridViewAdapter3;
import com.yyq58.activity.adapter.GridViewAdapter4;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.ChooseAcquirementBean;
import com.yyq58.activity.bean.MuHouBean;
import com.yyq58.activity.bean.TaiQianBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yyq58.activity.RegisterActivity.REQUEST_CHOOSE_QCQUIREMENT_CODE;

/****
 * 选择才艺 多选
 */
public class ChooseMultipleAcquirementActivity extends BaseActivity implements View.OnClickListener {

    private List<ChooseAcquirementBean.DataBean> mList;
    private Context mContext;
    private GridView gridView1,gridView2;
    private List<TaiQianBean> taiqianList = new ArrayList<>();
    private List<MuHouBean> muhouList = new ArrayList<>();
    private GridViewAdapter3 adapter3;
    private GridViewAdapter4 adapter4;
    private int extraCategoryType;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_acquirement);
        mContext = ChooseMultipleAcquirementActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("选择才艺", true, true);
        extraCategoryType = getIntent().getIntExtra("extra_category_type", 0);
        tvSet.setVisibility(View.VISIBLE);
        tvSet.setTextColor(getResources().getColor(R.color.white));
        tvSet.setText("保存");

        gridView1 = findViewById(R.id.gridview1);
        gridView2 = findViewById(R.id.gridview2);
        queryMineAcquirement();


        setListener();
    }

    private void setListener() {
        tvSet.setOnClickListener(this);
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
            case ConfigUtil.UPDATE_USER_LABEL_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("保存成功");
                    finish();
                }
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
                    int labelType = mList.get(j).getLabelType();
                    String labelId = mList.get(j).getLabelId();
                    String labelName = mList.get(j).getLabelName();
                    taiQianBean.setLabelId(labelId);
                    taiQianBean.setLabelName(labelName);
                    taiQianBean.setLabelType(labelType);
                    taiqianList.add(taiQianBean);
                } else {
                    muHouBean = new MuHouBean();
                    int labelType = mList.get(j).getLabelType();
                    String labelId = mList.get(j).getLabelId();
                    String labelName = mList.get(j).getLabelName();
                    muHouBean.setLabelId(labelId);
                    muHouBean.setLabelName(labelName);
                    muHouBean.setLabelType(labelType);
                    muhouList.add(muHouBean);
                }
            }
            adapter3 = new GridViewAdapter3(mContext, taiqianList);
            adapter4 = new GridViewAdapter4(mContext, muhouList);
            gridView1.setAdapter(adapter3);
            gridView2.setAdapter(adapter4);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_set:
                //id
                List<String> listTaiqian = adapter3.getListCategory();
                List<String> listMuhou = adapter4.getListCategory();
                //name
                List<String> listTaiqianCategoryName = adapter3.getListCategoryName();
                List<String> listMuhouCategoryName = adapter4.getListCategoryName();
                //type
                List<String> listTaiqianCategoryType = adapter3.getListCategoryType();
                List<String> listMuhouCategoryType = adapter4.getListCategoryType();

                String strTaiqian = StringUtils.listToString(listTaiqian, ',');
                String strMuhou = StringUtils.listToString(listMuhou, ',');

                String strTaiqianName = StringUtils.listToString(listTaiqianCategoryName, ',');
                String strMuhouName = StringUtils.listToString(listMuhouCategoryName, ',');

                String strTaiqianType = StringUtils.listToString(listTaiqianCategoryType, ',');
                String strMuhouType = StringUtils.listToString(listMuhouCategoryType, ',');

                String strName = "";
                String strId = "";
                String strType = "";
                if (extraCategoryType == 1) {
                    Intent intent = new Intent();
                    if (!StringUtils.isEmpty(strTaiqianName)) {
                        strName = strTaiqianName;
                        strId = strTaiqian;
                        strType = strTaiqianType;
                    }
                    if (!StringUtils.isEmpty(strMuhouName)) {
                        strName = strName + " , "+ strMuhouName;
                        strId = strId + strMuhou;
                        strType = strTaiqianType + "," + strMuhouType;
                    }

                    intent.putExtra("labelName", strName);
                    intent.putExtra("laeblId", strId);
                    intent.putExtra("labelType", strType);
                    setResult(REQUEST_CHOOSE_QCQUIREMENT_CODE, intent);
                    finish();
                } else {
                    updateUserLabel(strTaiqian + "," + strMuhou);
                }
                break;
        }
    }

    /****
     * 修改用户标签
     */
    private void updateUserLabel(String lableIds) {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("lableIds", lableIds);
        httpPostRequest(ConfigUtil.UPDATE_USER_LABEL_URL, params, ConfigUtil.UPDATE_USER_LABEL_URL_ACTION);
    }
}
