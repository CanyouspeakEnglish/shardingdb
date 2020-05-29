package com.p4.bank.mybatisconfig.parse;

import com.p4.bank.mybatisconfig.parse.type.TableParseType;
import com.p4.bank.utilnew.SubmeterService;
import com.p4.bank.utilnew.SubmeterType;
import org.springframework.util.ObjectUtils;

import java.util.Map;

public class TableParseHandleStringByModulusDefault implements TableParseHandle {
    /**
     * 取模值
     */
    private Map<String,Integer> delivery;

    @Override
    public synchronized String getTableNameSubmeter(String tableName, Object value) {
        Integer deliveryValue;
        if(this.delivery == null || (deliveryValue= delivery.get(tableName))== null){
            throw new IllegalArgumentException("取模取值为空");
        }
        return SubmeterService.delivery(tableName, SubmeterType.REMAINDER,value,deliveryValue);
    }

    @Override
    public TableParseType getType() {
        return TableParseType.MODULUS;
    }

    @Override
    public boolean isType(TableParseType type) {
        if(type == this.getType()){
            return true;
        }
        return false;
    }

    /**
     * 放入缓存
     * @param map
     */
    public void addDelivery(Map<String,Integer> map){
        if(this.isNull(map)){
            return;
        }
        this.delivery = map;
    }
    private boolean isNull(Map<String,Integer> map){
        return ObjectUtils.isEmpty(map);
    }

}
