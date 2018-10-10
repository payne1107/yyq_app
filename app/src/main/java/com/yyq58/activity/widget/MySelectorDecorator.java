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

/***
 * 选中的背景色
 */
public class MySelectorDecorator implements DayViewDecorator {
    private Context mContext;
    private CalendarDay date;

    public MySelectorDecorator(Context context) {
        mContext = context;
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void decorate(DayViewFacade view) {
        //设置字体颜色
        view.addSpan(new ForegroundColorSpan(Color.parseColor("#000000")));
        //设置背景色
        view.setSelectionDrawable(mContext.getDrawable(R.drawable.schedule_checked_bg));
    }

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}
