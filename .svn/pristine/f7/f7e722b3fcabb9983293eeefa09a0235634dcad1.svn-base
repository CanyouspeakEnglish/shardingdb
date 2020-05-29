package com.p4.bank.mybatisconfig;


import com.p4.bank.utilnew.AESUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lzx
 * AES 加密
 */
public class AESEncryptionHandle implements EncryptionHandle {
    /**
     * 加密处理
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public String encryption(String param) throws Exception {
        if(StringUtils.isBlank(param)){
            throw new IllegalArgumentException("AES 加密数据不能为空");
        }
        return AESUtils.encryption(param);
    }

    /**
     * 解密处理
     * @param value
     * @return
     * @throws Exception
     */
    @Override
    public String decrypt(String value) throws Exception {
        if(StringUtils.isBlank(value)){
            throw new IllegalArgumentException("AES 解密数据不能为空");
        }
        return AESUtils.decrypt(value);
    }
}
