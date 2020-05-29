package com.p4.bank.utilnew;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.util.Integers;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.util.*;

/**
 * @author lzx
 * 分表相关工具类
 */
 class SubmeterUtils {

    private static final Logger logger = LoggerFactory.getLogger(SubmeterUtils.class);

    private static final String MONTH = "MM";

    private static final String DAY = "dd";

    private static final String HOURS = "MM-dd-HH";

    private static final String JOINT = "_";

    public static  <T> List<String> submeter (String tableName
            ,SubmeterType submeterType, T ... param){
        if(ArrayUtils.isEmpty(param)){
            throw new NullPointerException("分表参数为空");
        }
        SubmeterParam submeterParam = new SubmeterParam(tableName,submeterType,param);
        Submeterhandle impl = SubmeterType.getImpl(submeterType);
        if(ObjectUtils.isEmpty(impl)){
            throw new NullPointerException("分表类型错误导致实现错误");
        }
        try {
            Submeterhandle vehical = (Submeterhandle) Proxy.newProxyInstance(impl.getClass().getClassLoader(),
                    impl.getClass().getInterfaces(), new SubmeterHandleDate<Submeterhandle>(impl));
            return vehical.submeter(submeterParam);
        } catch (Exception e){
            logger.error("SubmeterUtils submeter error={}",e);
        }
        return null;
    }


    static class SubmeterHandleDate<T extends Submeterhandle> implements InvocationHandler {
        private final T submeterhandle;

        public SubmeterHandleDate(T vehical){
            this.submeterhandle = vehical;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invoke = method.invoke(submeterhandle, args);
            return invoke;
        }
    }


    /**
     * 分表参数
     */
  private static class SubmeterParam<T>{
        /**
         * 表名
         */
        private String tableName;
        /**
         * 分表类型
         */
        private SubmeterType  submeterType;
        /**
         * 分表时间{可能为单个时间 也可能为时间段}
         */
        private  T[] param;

        public SubmeterParam(String tableName, SubmeterType submeterType, T[] dates) {
            this.tableName = tableName;
            this.submeterType = submeterType;
            this.param = dates;
        }

        public String getTableName() {
            return tableName;
        }

        public SubmeterType getSubmeterType() {
            return submeterType;
        }

        public T[] getParam() {
            return param;
        }
    }

    /**
     * 分表实现
     */
    interface Submeterhandle<T>{
         List<String> submeter(SubmeterParam<T> submeterParam);
    }

    /**
     * 按月分
     */
    protected static class SubmeterhandleMonth implements Submeterhandle<Date>{

        public SubmeterhandleMonth() {
        }

        @Override
        public  List<String> submeter(SubmeterParam<Date> submeterParam) {
            return submeterhandleMonth(submeterParam.getTableName(),submeterParam.getParam());
        }

        public List<String> submeterhandleMonth(String tableName,Date[] dates){
            List<String> returnMonth = Lists.newArrayList();
            List<Date> arrayList = new ArrayList(dates.length);
            Collections.addAll(arrayList,dates);
            Collections.sort(arrayList, new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    return o2.compareTo(o1);
                }
            });
            DateTimeFormatter formatter = DateTimeFormat.forPattern(MONTH);
            DateTime start = formatter.parseDateTime(DateUtils.toString(arrayList.get(arrayList.size()-1),MONTH));
            DateTime end = formatter.parseDateTime(DateUtils.toString(arrayList.get(0),MONTH));
            int months = Months.monthsBetween(start, end).getMonths();
            for(int i =0; i<=months ; i++){
                String dateStr = DateUtils.toString(DateUtils.stepMonth(arrayList.get(arrayList.size() - 1), i), MONTH);
                returnMonth.add(tableName+JOINT+dateStr);
            }
            return returnMonth;
        }

    }

    /**
     * 按天分
     */
    protected static class SubmeterhandleDay implements Submeterhandle<Date>{

        public SubmeterhandleDay() {
        }

        @Override
        public List<String> submeter(SubmeterParam<Date> submeterParam) {
            return submeterhandleDay(submeterParam.getTableName(),submeterParam.getParam());
        }

        public List<String> submeterhandleDay(String tableName,Date[] dates){
            List<String> returnDay = Lists.newArrayList();
            List<Date> arrayList = new ArrayList(dates.length);
            Collections.addAll(arrayList,dates);
            Collections.sort(arrayList, new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    return o2.compareTo(o1);
                }
            });
            DateTimeFormatter formatter = DateTimeFormat.forPattern(DAY);
            DateTime start = formatter.parseDateTime(DateUtils.toString(arrayList.get(arrayList.size()-1),DAY));
            DateTime end = formatter.parseDateTime(DateUtils.toString(arrayList.get(0),DAY));
            int days = Days.daysBetween(start, end).getDays();
            for(int i =0; i<=days ; i++){
                String dateStr = DateUtils.toString(DateUtils.stepDay(arrayList.get(arrayList.size() - 1), i), DAY);
                returnDay.add(tableName+JOINT+dateStr);
            }
            return returnDay;
        }

    }

    /**
     * 按小时分
     */
    protected static class SubmeterhandleHour implements Submeterhandle<Date>{

        public SubmeterhandleHour() {
        }

        @Override
        public List<String> submeter(SubmeterParam<Date> submeterParam) {
            return submeterhandleHour(submeterParam.getTableName(),submeterParam.getParam());
        }

        public List<String> submeterhandleHour(String tableName,Date[] dates){
            List<String> returnHour = Lists.newArrayList();
            List<Date> arrayList = new ArrayList(dates.length);
            Collections.addAll(arrayList,dates);
            Collections.sort(arrayList, new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    return o2.compareTo(o1);
                }
            });
            DateTimeFormatter formatter = DateTimeFormat.forPattern(HOURS);
            DateTime start = formatter.parseDateTime(DateUtils.toString(arrayList.get(arrayList.size()-1),HOURS));
            DateTime end = formatter.parseDateTime(DateUtils.toString(arrayList.get(0),HOURS));
            int hours = Hours.hoursBetween(start, end).getHours();
            for(int i =0; i<=hours ; i++){
                String dateStr = DateUtils.toString(DateUtils.stepHour(arrayList.get(arrayList.size() - 1), i), HOURS);
                returnHour.add(tableName+JOINT+dateStr);
            }
            return returnHour;
        }
    }

    /**
     * 按季度分
     */
    protected static class SubmeterhandleQuarter implements Submeterhandle<Date>{

        public SubmeterhandleQuarter() {
        }

        @Override
        public List<String> submeter(SubmeterParam<Date> submeterParam) {
            return submeterhandleQuarter(submeterParam.getTableName(),submeterParam.getParam());
        }

        public List<String> submeterhandleQuarter(String tableName,Date[] dates){
            List<String> returnQuarter = Lists.newArrayList();
            Set<String> quarterSet = Sets.newHashSet();
            List<Date> arrayList = new ArrayList(dates.length);
            Collections.addAll(arrayList,dates);
            Collections.sort(arrayList, new Comparator<Date>() {
                @Override
                public int compare(Date o1, Date o2) {
                    return o2.compareTo(o1);
                }
            });
            DateTimeFormatter formatter = DateTimeFormat.forPattern(DAY);
            DateTime start = formatter.parseDateTime(DateUtils.toString(arrayList.get(arrayList.size()-1),DAY));
            DateTime end = formatter.parseDateTime(DateUtils.toString(arrayList.get(0),DAY));
            int days = Days.daysBetween(start, end).getDays();
            for(int i =0; i<=days ; i++){
                final Date date = DateUtils.setDays(arrayList.get(arrayList.size() - 1), i);
                String dateStr = DateUtils.toString(date, DAY);
                String dateStrMM = DateUtils.toString(date, MONTH);
                quarterSet.add(tableName+JOINT+dateStrMM+JOINT+quarterPartition(dateStr));
            }
            returnQuarter.addAll(quarterSet);
            Collections.sort(returnQuarter, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            return returnQuarter;
        }

        /**
         * 判断每个月的上中下 0-10 01 11-20 02 >20 03
         * @param date
         * @return
         */
        private String quarterPartition(String date){
            String[] split = date.split("-");
            String day= split[2];
            String quarter = "";
            int i = Integer.parseInt(day);
            if(i>0 && i<=10){
                quarter = "01";
            }
            if(i>10 && i<=20){
                quarter = "02";
            }
            if(i>20){
                quarter = "03";
            }
            return quarter;
        }
    }

    /**
     * 数据取模
     */
    protected static class SubmeterhandleRemainder implements Submeterhandle<BigInteger>{

        public SubmeterhandleRemainder() {
        }

        @Override
        public List<String> submeter(SubmeterParam<BigInteger> submeterParam) {
            return submeterhandleRemainder(submeterParam.getTableName(),submeterParam.getParam());
        }

        public List<String> submeterhandleRemainder(String tableName,BigInteger[] integers){
            List<String> returnQuarter = Lists.newArrayList();
            BigInteger source = integers[0];
            BigInteger remainder = integers[1];
            BigInteger resultsBig = source.mod(remainder);
            int results = resultsBig.intValue();
            String result = results + "";
            if(results<=9){
                result = "0"+result;
            }
            returnQuarter.add(tableName+JOINT+result);
            return returnQuarter;
        }
    }
}
