package com.p4.bank.mybatisconfig;

import com.p4.bank.mybatisconfig.parse.TableParseHandle;
import com.p4.bank.mybatisconfig.parse.type.TableParseType;
import com.p4.bank.utilnew.GetTableNames;
import com.p4.bank.utilnew.MybatisSubmeterUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author lzx
 * sql 解析处理
 */
public class SqlParse {

    private Object parameterObject;

    private String sql;

    private boolean skip = false;

    public SqlParse(Object parameterObject, String sql) {
        this.parameterObject = parameterObject;
        this.sql = sql;
    }

    public String sqlHandle() throws Exception {
        return this.doSqlHandle();
    }

    private String doSqlHandle() throws Exception {
        Set<String> tableNames = GetTableNames.getTableNames(this.sql);
        for (String table : tableNames) {
        if (sql.contains(table) || sql.contains(table = table.toUpperCase())){
            TableSubmeterEntity tableSubmeterEntity = MybatisSubmeterUtils.getTableSubmeterEntity(table.toLowerCase());
            if(tableSubmeterEntity != null && StringUtils.isNotEmpty(tableSubmeterEntity.getField())){
                String field = tableSubmeterEntity.getField();
                skip = false;
                //批量单独处理
                    if(parameterObject instanceof Collection){
                        sqlParseCollection(parameterObject,table,field);
                        continue;
                    }
                    if(parameterObject.getClass().isArray()){
                        sqlParseArray(parameterObject,table,field);
                        continue;
                    }
                    if(parameterObject instanceof Object){
                        sqlParseObject(table, field,parameterObject);
                        continue;
                    }
                }
                //等于null 需要有特殊处理 可以选择其他的时间 或者 调用远程时间
            }
        }
        return sql;
    }

    private void sqlParseArray(Object parameterObject, String table, String field) throws Exception {
        Object[] array = (Object[]) parameterObject;
        if(!ArrayUtils.isNotEmpty(array)){
            for (Object parameterObjects : array) {
                sqlParseObject(table,field,parameterObjects);
                if (skip){
                    break;
                }
            }
        }
            }

        private void sqlParseCollection(Object parameterObject, String table, String field) throws Exception {
            Collection collection = (Collection) parameterObject;
            if(!CollectionUtils.isEmpty(collection)){
                for (Object parameterObjects : collection) {
                    sqlParseObject(table,field,parameterObjects);
                    if (skip){
                        break;
                }
            }
        }
    }

    private void sqlParseObject(String table, String field, Object parameterObjects) throws Exception {
        if(skip){
            return;
        }
        if(parameterObjects instanceof Map){
            doSqlParseMap(table,  field,parameterObjects);
        }else {
            doSqlParseObject(table, field,parameterObjects);
        }
    }

    private void doSqlParseMap(String table, String field, Object parameterObjects) throws Exception {
        if(skip){
            return;
        }
        Map<String,Object> map= (Map)parameterObjects;
        Set<String> strings = map.keySet();
        if(strings.contains(field)){
            Object value = map.get(field);
            sqlParseObject(table, value);
            skip = true;
        }else {
            Collection<Object> values = map.values();
            encryptionCollectionString(field,table, values);
        }
    }

    private void encryptionCollectionString(String field,String table, Collection<Object> values)throws Exception  {
        if(skip){
            return;
        }
        for (Object value : values) {
            if(value instanceof Collection){
                sqlParseCollection(value,table,field);
                continue;
            }
            if(value.getClass().isArray()){
                sqlParseArray(value,table,field);
                continue;
            }
            if(value instanceof Object){
                sqlParseObject(table, field,value);
                continue;
            }
        }
    }

    private void doSqlParseObject(String table, String field, Object parameterObjects) throws Exception {
        if(skip){
            return;
        }
        Class param = parameterObjects.getClass();
        while (param != null){
            List objectAttributeNamesSupper = DataDecryptionProcess.getObjectAttributeNames(param);
            if(!objectAttributeNamesSupper.contains(field)){
                param = param.getSuperclass();
            }else {
                break;
            }
        }
        if(param == null){
            return;
        }
        Field fieldValue = param.getDeclaredField(field);
        fieldValue.setAccessible(true);
        Object value = fieldValue.get(parameterObjects);
        sqlParseObject(table, value);
    }

    private void sqlParseObject(String table, Object value) throws Exception {
        if(skip){
            return;
        }
            if(!ObjectUtils.isEmpty(value)){
                TableSubmeterEntity tableSubmeterEntity = MybatisSubmeterUtils.getTableSubmeterEntity(table.toLowerCase());
                if(tableSubmeterEntity != null){
                    TableParseHandle tableParseHandle = MybatisSubmeterUtils.getTableParseHandle(tableSubmeterEntity.getParseType());
                    String tableNameNew = tableParseHandle.getTableNameSubmeter(table,value);
                    sql = sql.replace(table,tableNameNew);
                    skip = true;
                }
        }
    }




}
