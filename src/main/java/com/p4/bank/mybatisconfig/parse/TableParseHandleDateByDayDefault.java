package com.p4.bank.mybatisconfig.parse;

import com.p4.bank.mybatisconfig.parse.type.TableParseType;
import com.p4.bank.utilnew.DateUtils;
import com.p4.bank.utilnew.SubmeterService;
import com.p4.bank.utilnew.SubmeterType;

import java.util.Date;

public class TableParseHandleDateByDayDefault implements TableParseHandle {

    @Override
    public String getTableNameSubmeter(String tableName, Object value)  throws Exception {
        if(value instanceof  Date){
            value = (Date) value;
        }else {
            value = DateUtils.parseDate(value.toString(), DateUtils.DATE_FORMAT_DATETIME);
        }
        return SubmeterService.submeter(tableName, SubmeterType.DAY, (Date) value);
    }

    @Override
    public TableParseType getType() {
        return TableParseType.DEFAULT;
    }

    @Override
    public boolean isType(TableParseType type) {
        if(type == this.getType()){
            return true;
        }
        return false;
    }
}
