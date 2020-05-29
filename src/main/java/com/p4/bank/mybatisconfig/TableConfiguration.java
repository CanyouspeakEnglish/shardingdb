package com.p4.bank.mybatisconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lzx
 * 配置类
 */
@Data
@ConfigurationProperties(prefix = "submeter")
public class TableConfiguration {

    private String entitys;
    /**
     * 加密字段
     */
    private String fields;
    /**
     * 上线截止时间
     */
    private String time;

    /**
     * 解析类型扩展
     */
    private String parsings;
}
