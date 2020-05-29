package com.p4.bank.utilnew;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 分表类型
 * @author lzx
 */
public enum SubmeterType{
    /**
     * 按月分
     */
    MONTH
    /**
     * 按季度
     */
    ,QUARTER,
    /**
     * 按天分
     */
    DAY,
    /**
     * 按小时
     */
    HOURS,
    /**
     * 数据取余
     */
    REMAINDER;
    /**
     * 分表策略对应实现缓存
     */
    private static Map<SubmeterType,SubmeterUtils.Submeterhandle> impl = Maps.newHashMap();

    /**
     * 初始化缓存
     */
    static {
        impl.put(SubmeterType.MONTH,new SubmeterUtils.SubmeterhandleMonth());
        impl.put(SubmeterType.QUARTER, new SubmeterUtils.SubmeterhandleQuarter());
        impl.put(SubmeterType.DAY,new SubmeterUtils.SubmeterhandleDay());
        impl.put(SubmeterType.HOURS,new SubmeterUtils.SubmeterhandleHour());
        impl.put(SubmeterType.REMAINDER,new SubmeterUtils.SubmeterhandleRemainder());
    }

    public static SubmeterUtils.Submeterhandle getImpl (SubmeterType submeterType){
        return impl.get(submeterType);
    }

}
