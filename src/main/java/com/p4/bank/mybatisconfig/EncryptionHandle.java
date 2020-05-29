package com.p4.bank.mybatisconfig;

/**
 * @author lzx
 * 加密处理接口
 */
public interface EncryptionHandle {
    /**
     * 加密
     * @param param
     * @return
     */
    String encryption(String param) throws Exception;

    /**
     * 解密
     * @param value
     * @return
     */
    String decrypt(String value) throws Exception;

}
