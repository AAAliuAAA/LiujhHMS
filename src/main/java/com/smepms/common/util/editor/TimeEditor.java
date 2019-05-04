package com.smepms.common.util.editor;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ljh on 2018/2/8.
 */
public class TimeEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text){
        try {
            if(text==null||text.equals("")){
                throw new NullPointerException();
            }
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date checkDate = dateFormat.parse(text);
            Time checkTime = new Time(checkDate.getTime());
            setValue(checkTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
