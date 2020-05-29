package com.p4.bank.utilnew;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

public abstract class AbstractTimeDifferenceUtils {

    private String timeFormat = "yyyy-MM-dd HH:mm:ss";

    public List<Date> getTimeDifference(Date[] dates){
        List<Date> returnDay = Lists.newArrayList();
        List<Date> arrayList = new ArrayList(dates.length);
        Collections.addAll(arrayList,dates);
        Collections.sort(arrayList, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });
        DateTimeFormatter formatter = DateTimeFormat.forPattern(timeFormat);
        DateTime start = formatter.parseDateTime(DateUtils.toString(arrayList.get(arrayList.size()-1),timeFormat));
        DateTime end = formatter.parseDateTime(DateUtils.toString(arrayList.get(0),timeFormat));
        int days = Days.daysBetween(start, end).getDays();
        for(int i =0; i<=days ; i++){
            returnDay.add(stepHandle(arrayList.get(arrayList.size() - 1), i));
        }
        List<Date> returnDayDesc = Lists.newArrayList();
        for (int i = 0; i < returnDay.size(); i++) {
            returnDayDesc.add(returnDay.get((returnDay.size()-1)-i));
        }
        return returnDayDesc;
    }

    abstract  Date stepHandle(Date date,int step);


}
