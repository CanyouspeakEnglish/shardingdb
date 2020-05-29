package com.p4.bank.utilnew;


import com.p4.bank.mybatisconfig.*;
import com.p4.bank.mybatisconfig.parse.TableParseHandle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lzx
 * 分页工具类
 */
public class MybatisSubmeterUtils {


    private static Map<String,TableSubmeterEntity> tableSubmeterEntitys;

    private static Map<String,EncryptionEntity> encryptionEntitys;

    private static Map<String,TableParseHandle> tableParseHandle;

    private static Map<String,EncryptionHandle> encryptionHandle = new HashMap<String,EncryptionHandle>();


    public static int MAPPED_STATEMENT_INDEX = 0;

    public static int PARAMETER_INDEX = 1;

    public static int ROWBOUNDS_INDEX = 2;

    public static int RESULT_HANDLER_INDEX = 3;

    private MybatisSubmeterUtils(){
        throw new IllegalArgumentException("工具类不需要构造");
    }

    static {
        encryptionHandle.put("AES",new AESEncryptionHandle());
        encryptionHandle.put("DES",new DESEncryptionHandle());
        encryptionHandle.put("THREEDES",new ThreeDesEncryptionHandle());
    }


    public static Set<String> getEncryptionFields(){
        return encryptionEntitys.keySet();
    }

    public static EncryptionHandle getEncryptionHandle(String key){
        if(encryptionEntitys == null || encryptionEntitys.get(key) == null){
            return null;
        }
        EncryptionEntity encryptionEntity = encryptionEntitys.get(key);
        return encryptionHandle.get(encryptionEntity.getEncryptionType());
    }

    /**
     * 获取解密字段
     * @param key
     * @return
     */
    public static EncryptionEntity getEncryptionEntity(String key){
        if (encryptionEntitys == null){
            return null;
        }
        return encryptionEntitys.get(key);
    }
    /**
     * 初始化submeterEntitys
     * @param submeterEntitys
     */
    public static void initTableSubmeterEntitys(Map<String,TableSubmeterEntity> submeterEntitys){
        tableSubmeterEntitys = submeterEntitys;
    }

    public static void initEncryptedFields(Map<String,EncryptionEntity> encryptionEntity) {

        encryptionEntitys = encryptionEntity;
    }

    public static void initExpandParsings(Map<String,TableParseHandle> tableParseHandles) {
        tableParseHandle = tableParseHandles;
    }


    public static TableSubmeterEntity getTableSubmeterEntity(String tableName) {
        if(tableSubmeterEntitys == null){
            return null;
        }
        return tableSubmeterEntitys.get(tableName);
    }

    public static TableParseHandle getTableParseHandle(String parseType) {
        if(tableParseHandle == null){
            return null;
        }
        return tableParseHandle.get(parseType);
    }
}
