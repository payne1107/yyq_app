package com.yyq58.activity.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.yyq58.R;

import java.util.Date;
import java.util.List;

/***
 * 和当天日期相等不覆盖默认背景色
 */
public class EventDecorator implements DayViewDecorator {
    private List<Date> dates;
    private Context mContext;
    public EventDecorator(List<Date> dates,Context context) {
        this.dates = dates;
        mContext = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day.getDate());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void decorate(DayViewFacade view) {
        //设置字体颜色
        view.addSpan(new ForegroundColorSpan(Color.parseColor("#000000")));
        //设置背景色
        view.setSelectionDrawable(mContext.getDrawable(R.drawable.schedule_bg));
    }
}
