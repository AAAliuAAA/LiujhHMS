package com.smepms.workattendance.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 比较两个传入的日期对象的当日时间，
     * 如果前者大于后者 则返回true
     * @param mainDate
     * @param comDate
     * @return
     */
    public static boolean compareDate(Date mainDate,Date comDate) {
        Calendar mainCalendar = Calendar.getInstance();
        Calendar comCalendar = Calendar.getInstance();
        mainCalendar.setTime(mainDate);
        comCalendar.setTime(comDate);
        if (mainCalendar.get(Calendar.HOUR_OF_DAY) > comCalendar.get(Calendar.HOUR_OF_DAY)) {
            return true;
        }else if(mainCalendar.get(Calendar.HOUR_OF_DAY) == comCalendar.get(Calendar.HOUR_OF_DAY)){
            if (mainCalendar.get(Calendar.MINUTE) > comCalendar.get(Calendar.MINUTE)) {
                return true;
            }else if(mainCalendar.get(Calendar.MINUTE) == comCalendar.get(Calendar.MINUTE)){
                if (mainCalendar.get(Calendar.SECOND) > comCalendar.get(Calendar.SECOND)) {
                    return true;
                }
            }
        }
        return false;
    }
}
