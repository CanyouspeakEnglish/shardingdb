package com.p4.bank.mybatisconfig;

import com.p4.bank.utilnew.TableInitializationUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * start 启动类
 */
@Configuration
@EnableConfigurationProperties(TableConfiguration.class)
public class MybatisInterceptorConfig {

    @Autowired
    private TableConfiguration tableConfiguration;
    /**
     * 分表配置
     *//*
    @Value("${table_submeter_entitys}")
    private String tableSubmeterEntitys;
    *//**
     * 加密字段
     *//*
    @Value("${encrypted_field}")
    private String encryptedFields;
    *//**
     * 上线截止时间
     *//*
    @Value("${online_cut_off_time}")
    private String onlineCutOffTime;

    *//**
     * 解析类型扩展
     *//*
    @Value("${expand_parsing}")
    private String expandParsings;*/

    @Bean
    public String myInterceptor(SqlSessionFactory sqlSessionFactory) {
        new TableInitializationUtils(tableConfiguration.getEntitys(),tableConfiguration.getFields(),tableConfiguration.getParsings(),this);
        sqlSessionFactory.getConfiguration().addInterceptor(new MybatisExecutorInterceptor());
        sqlSessionFactory.getConfiguration().addInterceptor(new MybatisResultSetInterceptor());
        return "interceptor";
    }

    public String getOnlineCutOffTime() {
        return tableConfiguration.getTime();
    }
}
