package com.p4.bank.mybatisconfig.parse;

import com.p4.bank.mybatisconfig.parse.type.TableParseType;
import com.p4.bank.utilnew.DateUtils;
import com.p4.bank.utilnew.SubmeterService;
import com.p4.bank.utilnew.SubmeterType;

public class TableParseHandleStringByDayDefault implements TableParseHandle {

    @Override
    public String getTableNameSubmeter(String tableName, Object value) throws Exception {
        return SubmeterService.submeter(tableName, SubmeterType.DAY, DateUtils.parseDate(value.toString(), DateUtils.DATE_FORMAT_DATETIME));
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
