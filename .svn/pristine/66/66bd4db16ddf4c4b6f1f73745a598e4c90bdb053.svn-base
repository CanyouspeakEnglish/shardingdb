package com.p4.bank.mybatisconfig.parse;

import com.p4.bank.mybatisconfig.parse.type.TableParseType;
import com.p4.bank.utilnew.SubmeterService;
import com.p4.bank.utilnew.SubmeterType;

import java.util.Date;

public class TableParseHandleDateByMonthDefault implements TableParseHandle {

    @Override
    public String getTableNameSubmeter(String tableName, Object value) throws Exception {
        return SubmeterService.submeter(tableName, SubmeterType.MONTH, (Date) value);
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
