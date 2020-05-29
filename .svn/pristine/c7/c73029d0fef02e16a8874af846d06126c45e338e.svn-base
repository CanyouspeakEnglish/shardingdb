package com.p4.bank.utilnew;

import com.p4.bank.mybatisconfig.EncryptionEntity;
import com.p4.bank.mybatisconfig.MybatisInterceptorConfig;
import com.p4.bank.mybatisconfig.TableSubmeterEntity;
import com.p4.bank.mybatisconfig.parse.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class TableInitializationUtils {

    private final static String INITIALSEGMENTATION =",";
    private final static String SECONDINITIALSEGMENTATION ="#";

    private String tableSubmeterEntitys;

    private String encryptedFields;

    private String expandParsings;

    private MybatisInterceptorConfig mybatisInterceptorConfig;

    private Map<String,Integer> modulusMaps;

    public TableInitializationUtils(String tableSubmeterEntitys, String encryptedFields, String expandParsings
            ,MybatisInterceptorConfig mybatisInterceptorConfig) {
        this.tableSubmeterEntitys = tableSubmeterEntitys;
        this.encryptedFields = encryptedFields;
        this.expandParsings = expandParsings;
        this.mybatisInterceptorConfig = mybatisInterceptorConfig;
        initTableSubmeterEntitys();
        initEncryptedFields();
        initExpandParsings();
    }

    /**
     * 解析类型初始化
     */
    private void initExpandParsings() {
        Map<String,TableParseHandle> maps =  new HashMap<String,TableParseHandle>();
        if(StringUtils.isNotBlank(expandParsings)){
            String[] split = expandParsings.split(INITIALSEGMENTATION);
            if(!ArrayUtils.isEmpty(split)){
                for (String tableParseHandle : split) {
                    if(StringUtils.isNotBlank(tableParseHandle)){
                        String[] tableParseHandles = tableParseHandle.split(SECONDINITIALSEGMENTATION);
                        if(!ArrayUtils.isEmpty(tableParseHandles)){
                            try {
                                Object tableParseHandleNew = Class.forName(tableParseHandles[1]).newInstance();
                                if(tableParseHandleNew instanceof  TableParseHandle){
                                    maps.put(tableParseHandles[0],(TableParseHandle)tableParseHandleNew);
                                }
                            } catch (Exception e) {
                                log.error("initExpandParsings error={}",e);
                            }
                        }

                    }
                }

            }
        }
        initDefault(maps);
        MybatisSubmeterUtils.initExpandParsings(maps);
    }

    private void initDefault(Map<String, TableParseHandle> maps) {
        TableParseHandleDateByDayDefault tableParseHandleDateByDayDefault = new TableParseHandleDateByDayDefault();
        TableParseHandleDateByMonthDefault tableParseHandleDateByMonthDefault = new TableParseHandleDateByMonthDefault();
        TableParseHandleStringByDayDefault tableParseHandleStringByDayDefault = new TableParseHandleStringByDayDefault();
        TableParseHandleStringByModulusDefault tableParseHandleStringByModulusDefault = new TableParseHandleStringByModulusDefault();
        tableParseHandleStringByModulusDefault.addDelivery(this.modulusMaps);
        maps.put("dateByDayDefault",tableParseHandleDateByDayDefault);
        maps.put("dateByMonthDefault",tableParseHandleDateByMonthDefault);
        maps.put("stringByDayDefault",tableParseHandleStringByDayDefault);
        maps.put("stringByModulusDefault",tableParseHandleStringByModulusDefault);
    }

    /**
     * 加密字段初始化
     */
    private void initEncryptedFields() {

        if(StringUtils.isNotBlank(encryptedFields)){
            String[] split = encryptedFields.split(INITIALSEGMENTATION);
            Map<String,EncryptionEntity> maps =  new HashMap<String,EncryptionEntity>();
            if(!ArrayUtils.isEmpty(split)){
                for (String encryptionEntity : split) {
                    if(StringUtils.isNotBlank(encryptionEntity)){
                        String[] encryptionEntitys = encryptionEntity.split(SECONDINITIALSEGMENTATION);
                        if(!ArrayUtils.isEmpty(encryptionEntitys)){
                            EncryptionEntity encryption = new EncryptionEntity();
                            encryption.setEncryptedField(encryptionEntitys[0]);
                            encryption.setEncryptionType(encryptionEntitys[1]);
                            encryption.setMybatisInterceptorConfig(mybatisInterceptorConfig);
                            encryption.setIsDecryption(encryptionEntitys[2]);
                            maps.put(encryptionEntitys[0],encryption);
                        }

                    }
                }
                MybatisSubmeterUtils.initEncryptedFields(maps);
            }
        }
    }

    /**
     * 初始化
     */
    private void initTableSubmeterEntitys() {
        Map<String,TableSubmeterEntity> maps=  new HashMap<String,TableSubmeterEntity>();
        if(StringUtils.isNotBlank(tableSubmeterEntitys)){
            String[] split = tableSubmeterEntitys.split(INITIALSEGMENTATION);
            if(!ArrayUtils.isEmpty(split)){
                for (String submeterEntity : split) {
                    if(StringUtils.isNotBlank(submeterEntity)){
                        String[] submeterEntityStrs = submeterEntity.split(SECONDINITIALSEGMENTATION);
                        if(!ArrayUtils.isEmpty(submeterEntityStrs)){
                            int length = submeterEntityStrs.length;
                            TableSubmeterEntity tableSubmeterEntity = new TableSubmeterEntity();
                            tableSubmeterEntity.setTableName(submeterEntityStrs[0]);
                            tableSubmeterEntity.setField(submeterEntityStrs[1]);
                            tableSubmeterEntity.setParseType(submeterEntityStrs[2]);
                            if(length>3){
                                if(modulusMaps == null){
                                    modulusMaps = new HashMap<String, Integer>();
                                }
                                modulusMaps.put(submeterEntityStrs[0],Integer.parseInt(submeterEntityStrs[3]));
                                tableSubmeterEntity.setDelivery(submeterEntityStrs[3]);
                            }
                            maps.put(submeterEntityStrs[0],tableSubmeterEntity);
                        }

                    }
                }
                MybatisSubmeterUtils.initTableSubmeterEntitys(maps);
            }
        }
    }
}
