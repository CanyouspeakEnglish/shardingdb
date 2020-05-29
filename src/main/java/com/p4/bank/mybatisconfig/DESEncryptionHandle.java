package com.p4.bank.mybatisconfig;

import com.p4.bank.utilnew.EncryUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lzx
 * des 加解密
 */
public class DESEncryptionHandle implements EncryptionHandle {
    @Override
    public String encryption(String param) throws Exception {
        if(StringUtils.isBlank(param)){
            throw new IllegalArgumentException("DES 加密数据不能为空");
        }
        return EncryUtil.encrypt(param);
    }

    @Override
    public String decrypt(String value) throws Exception {
        if(StringUtils.isBlank(value)){
            throw new IllegalArgumentException("DES 解密数据不能为空");
        }
        return EncryUtil.decrypt(value);
    }
}
