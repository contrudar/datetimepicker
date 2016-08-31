package com.fourmob.datetimepicker.date;

public interface DatePickerController {
    int getFirstDayOfWeek();

    int getMaxYear();

    int getMinYear();

    int getStartMonth();

    int getEndMonth();

    int getStartDay();

    int getEndDay();

    SimpleMonthAdapter.CalendarDay getSelectedDay();

    void onDayOfMonthSelected(int year, int month, int day);

    void onYearSelected(int year);

    void registerOnDateChangedListener(OnDateChangedListener onDateChangedListener);

    void tryVibrate();
}