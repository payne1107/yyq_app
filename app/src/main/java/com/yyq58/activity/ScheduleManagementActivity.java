package com.yyq58.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.yyq58.R;
import com.yyq58.activity.adapter.ScheduleManagementAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.MonthScheduleBean;
import com.yyq58.activity.bean.ScheduleListBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.DateUtils;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.EventDecorator;
import com.yyq58.activity.widget.HighlightWeekendsDecorator;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyDialog;
import com.yyq58.activity.widget.MyListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//档期管理
@RequiresApi(api = Build.VERSION_CODES.O)
public class ScheduleManagementActivity extends BaseActivity implements View.OnClickListener,OnDateSetListener {

    private TextView tvChooseDate;
    private TimePickerDialog dialogDay;
    private String selectDate;
    private EditText etContent;
    private EditText etUseranme;
    private EditText etLocation;
    private String strHours;
    private MyListView listView;
    private Context mContext;
    private List<ScheduleListBean.DataBean> mList = new ArrayList<>();
    private ScheduleManagementAdapter adapter;
    private MaterialCalendarView calendarView;
    private boolean isEdit = false;
    private String scheduleId;
    private String clickYear;
    private String clickMonth;
    private String clickDay;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_schedule_management);
        mContext = ScheduleManagementActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("档期管理", true, true);
        tvSet.setText("发布");
        tvSet.setVisibility(View.VISIBLE);
        tvChooseDate = findViewById(R.id.tv_choose_date);
        etContent = findViewById(R.id.et_content);
        etUseranme = findViewById(R.id.et_username);
        etLocation = findViewById(R.id.et_location);
        listView = findViewById(R.id.listView);

        adapter = new ScheduleManagementAdapter(mContext,mList);
        listView.setAdapter(adapter);


        initViewDateDialog(this, System.currentTimeMillis() - ConfigUtil.TenYears);

        calendarView = findViewById(R.id.calendarView);
        final CalendarDay today = CalendarDay.from(DateUtils.getYear(), DateUtils.getMonth(), DateUtils.getCurrentDayOfMonth());
        calendarView.setCurrentDate(today);
        calendarView.setSelectedDate(today);
        //添加修饰
        calendarView.addDecorator(new HighlightWeekendsDecorator());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                isEdit = false;
                SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
                try {
                    Date newDate = sf1.parse(String.valueOf(date.getDate()));
                    SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
                    selectDate = sf2.format(newDate);
                    List<String> list = StringUtils.stringsToList(selectDate, "-");
                    clickYear = list.get(0);
                    clickMonth = list.get(1);
                    clickDay = list.get(2);
                    queryReleaseScheduleForDay(clickYear, clickMonth, clickDay);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        setListener();
    }

    private void setListener() {
        tvChooseDate.setOnClickListener(this);
        tvSet.setOnClickListener(this);

        adapter.setOnItemClickListener(new IButtonClickListener() {


            @Override
            public void onEditClick(View view, int position) {
                isEdit = true;
                ScheduleListBean.DataBean bean = mList.get(position);
                if (bean != null) {
                    String detailTime = bean.getDetailTime();
                    String place = bean.getPlace();
                    String content = bean.getContent();
                    String linkman = bean.getLinkMan();
                    strHours = detailTime;
                    scheduleId = bean.getId();
                    tvChooseDate.setText(StringUtils.isEmpty(detailTime) ? "" : detailTime);
                    etContent.setText(StringUtils.isEmpty(content) ? "" : content);
                    etLocation.setText(StringUtils.isEmpty(place) ? "" : place);
                    etUseranme.setText(StringUtils.isEmpty(linkman) ? "" : linkman);
                }
            }

            @Override
            public void onDeleClick(View view, int position) {
                ScheduleListBean.DataBean bean = mList.get(position);
                if (bean != null) {
                    scheduleId = bean.getId();
                }
                final MyDialog dialog = new MyDialog(mContext);
                dialog.setMessage("是否删除档期？");
                dialog.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteSchedule(scheduleId);
                        dialog.dismiss();
                    }
                });
                dialog.setOnNegativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

            @Override
            public void onSaveClick(View view, int position) {

            }
        });
    }

    /****
     * 删除档期
     * @param scheduleId
     */
    private void deleteSchedule(String scheduleId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", scheduleId);
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.DELETE_SCHEDULE_LIST_URL, params, ConfigUtil.DELETE_SCHEDULE_LIST_URL_ACTION);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_date:
                //选择从业时间
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.activity_set:
                if (isEdit) {
                    editSchedule();
                } else {
                    releaseSchedule();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryReleaseScheduleForMonth();
    }

    /***
     * 根据月份查询档期
     */
    private void queryReleaseScheduleForMonth() {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId",MyApplication.userId);
        params.put("year", String.valueOf(DateUtils.getYear()));
        params.put("month", String.valueOf((DateUtils.getMonth() + 1)));
        httpPostRequest(ConfigUtil.QUERY_BY_MONTH_SCHEDULE_URL, params, ConfigUtil.QUERY_BY_MONTH_SCHEDULE_URL_ACTION);
    }

    /***
     * 根据日查询当期
     * @param year
     * @param month
     * @param day
     */
    private void queryReleaseScheduleForDay( String year, String month, String day) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId",MyApplication.userId);
        params.put("year", year);
        params.put("month", month);
        params.put("day", day);
        httpPostRequest(ConfigUtil.QUERY_BY_DAY_SCHEDULE_URL, params, ConfigUtil.QUERY_BY_DAY_SCHEDULE_URL_ACTION);
    }

    //发布档期
    private void releaseSchedule() {
        if (StringUtils.isEmpty(strHours)) {
            toastMessage("请选择档期时间");
            return;
        }
        String conent = etContent.getText().toString().trim();
        String location =  etLocation.getText().toString().trim();
        String username = etUseranme.getText().toString().trim();
        if (StringUtils.isEmpty(conent)) {
            toastMessage("档期内容不能为空");
            return;
        }
        if (StringUtils.isEmpty(location)) {
            toastMessage("档期地点不能为空");
            return;
        }
        if (StringUtils.isEmpty(username)) {
            toastMessage("联系人不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("detailTime",strHours);
        if (StringUtils.isEmpty(selectDate)) {
            int year = DateUtils.getYear();
            int month = DateUtils.getMonth() + 1;
            int day = DateUtils.getCurrentDayOfMonth();
            params.put("year", String.valueOf(year));
            params.put("month", String.valueOf(month));
            params.put("day", String.valueOf(day));
        } else {
            List<String> list = StringUtils.stringsToList(selectDate, "-");
            String year = list.get(0);
            String month =list.get(1);
            String day = list.get(2);
            params.put("year", year);
            params.put("month", month);
            params.put("day", day);
        }
        params.put("place",location);
        params.put("content",conent);
        params.put("linkMan", username);
        startIOSDialogLoading(mContext, "");
        httpPostRequest(ConfigUtil.ADD_CONSUMER_SCHEDULE_URL, params, ConfigUtil.ADD_CONSUMER_SCHEDULE_URL_ACTION);
    }


    //编辑档期
    private void editSchedule() {
        if (StringUtils.isEmpty(strHours)) {
            toastMessage("请选择档期时间");
            return;
        }
        String conent = etContent.getText().toString().trim();
        String location =  etLocation.getText().toString().trim();
        String username = etUseranme.getText().toString().trim();
        if (StringUtils.isEmpty(conent)) {
            toastMessage("档期内容不能为空");
            return;
        }
        if (StringUtils.isEmpty(location)) {
            toastMessage("档期地点不能为空");
            return;
        }
        if (StringUtils.isEmpty(username)) {
            toastMessage("联系人不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("detailTime",strHours);
        params.put("id", scheduleId);
        if (StringUtils.isEmpty(selectDate)) {
            int year = DateUtils.getYear();
            int month = DateUtils.getMonth() + 1;
            int day = DateUtils.getCurrentDayOfMonth();
            params.put("year", String.valueOf(year));
            params.put("month", String.valueOf(month));
            params.put("day", String.valueOf(day));
        } else {
            List<String> list = StringUtils.stringsToList(selectDate, "-");
            String year = list.get(0);
            String month =list.get(1);
            String day = list.get(2);
            params.put("year", year);
            params.put("month", month);
            params.put("day", day);
        }
        params.put("place",location);
        params.put("content",conent);
        params.put("linkMan", username);
        startIOSDialogLoading(mContext, "");
        httpPostRequest(ConfigUtil.EDIT_SCHEDULE_LIST_URL, params, ConfigUtil.EDIT_SCHEDULE_LIST_URL_ACTIOn);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.ADD_CONSUMER_SCHEDULE_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("档期发布成功");
                    finish();
                }
                break;
            case ConfigUtil.QUERY_BY_MONTH_SCHEDULE_URL_ACTION:
                handleQueryByMonthSchedule(json);
                break;
            case ConfigUtil.QUERY_BY_DAY_SCHEDULE_URL_ACTION:
                handleQueryByDayScheduleList(json);
                break;
            case ConfigUtil.EDIT_SCHEDULE_LIST_URL_ACTIOn:
                if (getRequestCode(json) == 1000) {
                    toastMessage("档期编辑成功");
                    finish();
                }
                break;
            case ConfigUtil.DELETE_SCHEDULE_LIST_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("删除成功");
                    queryReleaseScheduleForDay(clickYear, clickMonth, clickDay);
                }
                break;
        }
    }

    private void handleQueryByDayScheduleList(String json) {
        ScheduleListBean bean = JSON.parseObject(json, ScheduleListBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                adapter.setData(mList);
            } else {
                mList = new ArrayList<>();
                adapter.setData(mList);
            }
        }
    }

    private void handleQueryByMonthSchedule(String json) {
        List<Date> list = new ArrayList<>();
        MonthScheduleBean bean = JSON.parseObject(json, MonthScheduleBean.class);
        if (bean != null) {
            List<MonthScheduleBean.DataBean> data = bean.getData();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getDay() != DateUtils.getCurrentDayOfMonth()) {
                    String date = data.get(i).getYear() + "-" + data.get(i).getMonth() + "-" + data.get(i).getDay();
                    list.add(StringUtils.string2Date(date, "yyyy-MM-dd"));
                }
            }
            //增加修饰符
            calendarView.addDecorator(new EventDecorator(list,mContext));
        }
    }

    /**
     * 初始化时间选择器
     */
    protected void initViewDateDialog(OnDateSetListener listener, long currentMillSeconds) {
        /*System.currentTimeMillis() - ConfigUtil.TenYears*///设置当前日期
        dialogDay = new TimePickerDialog.Builder()
                .setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - ConfigUtil.TenYears8)
                .setMaxMillseconds(System.currentTimeMillis() + ConfigUtil.TenYears)
                .setCurrentMillseconds(currentMillSeconds /*System.currentTimeMillis() - ConfigUtil.TenYears*/)//设置当前日期
                .setThemeColor(getResources().getColor(R.color.cccccc))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.text_color_9))
                .setWheelItemTextSize(16)
                .build();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        strHours = sf.format(d);
        if (StringUtils.isEmpty(selectDate)) {
            String newDate = DateUtils.getYear() + "-" + (DateUtils.getMonth() + 1) + "-" + DateUtils.getCurrentDayOfMonth();
            tvChooseDate.setText(newDate + " " + strHours);
        } else {
            tvChooseDate.setText(selectDate + " " + strHours);
        }
    }
}
