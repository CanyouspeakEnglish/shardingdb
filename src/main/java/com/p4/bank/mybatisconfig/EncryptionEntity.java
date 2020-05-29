package com.p4.bank.mybatisconfig;

/**
 * @author lzx
 * 加密字段
 */
public class EncryptionEntity {

    private String encryptedField;

    private String encryptionType;

    private MybatisInterceptorConfig mybatisInterceptorConfig;

    private String isDecryption;


    public String getEncryptedField() {
        return encryptedField;
    }

    public void setEncryptedField(String encryptedField) {
        this.encryptedField = encryptedField;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public MybatisInterceptorConfig getMybatisInterceptorConfig() {
        return mybatisInterceptorConfig;
    }

    public void setMybatisInterceptorConfig(MybatisInterceptorConfig mybatisInterceptorConfig) {
        this.mybatisInterceptorConfig = mybatisInterceptorConfig;
    }

    public String getIsDecryption() {
        return isDecryption;
    }

    public void setIsDecryption(String isDecryption) {
        this.isDecryption = isDecryption;
    }
}
