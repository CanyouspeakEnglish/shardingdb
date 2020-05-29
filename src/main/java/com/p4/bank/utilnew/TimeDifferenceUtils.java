package com.p4.bank.utilnew;


import java.util.*;

public class TimeDifferenceUtils {

    private static AbstractTimeDifferenceUtils day = new TimeDifferenceDay();

    private static AbstractTimeDifferenceUtils month = new TimeDifferenceMonth();

    private static AbstractTimeDifferenceUtils hour = new TimeDifferenceHour();

    /**
     * 相差天数倒序
     * @param dates
     * @return
     */
    public static List<Date> getDayTimeDifference(Date[] dates){
        return day.getTimeDifference(dates);
    }
    /**
     * 相差月数倒序
     * @param dates
     * @return
     */
    public static List<Date> getMonthTimeDifference(Date[] dates){
        return month.getTimeDifference(dates);
    }


    public static List<Date> getHourTimeDifference(Date[] dates){
        return hour.getTimeDifference(dates);
    }


   private static class TimeDifferenceDay extends AbstractTimeDifferenceUtils{

        @Override
        Date stepHandle(Date date, int step) {
            return DateUtils.stepDay(date,step);
        }

    }

   private static class TimeDifferenceMonth extends AbstractTimeDifferenceUtils{

        @Override
        Date stepHandle(Date date, int step) {
            return DateUtils.stepMonth(date,step);
        }

    }

   private static class TimeDifferenceHour extends AbstractTimeDifferenceUtils{

        @Override
        Date stepHandle(Date date, int step) {
            return DateUtils.stepHour(date,step);
        }

    }
}
