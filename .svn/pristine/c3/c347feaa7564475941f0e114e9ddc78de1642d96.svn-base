package com.p4.bank.mybatisconfig.parse;

import com.p4.bank.mybatisconfig.parse.type.TableParseType;


/**
 * @author lzx
 * 处理表名解析类
 * 可用于扩展解析类型
 */
public interface TableParseHandle {
    /**
     * 获取表名
     * @param tableName
     * @param value
     * @return
     */
    String getTableNameSubmeter(String tableName,Object value) throws Exception;

    TableParseType getType();

    boolean isType(TableParseType type);
}
