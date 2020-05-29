package com.p4.bank.mybatisconfig;


import com.p4.bank.utilnew.ThreeDesUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lzx
 *
 *ThreeDes 加密
 */
public class ThreeDesEncryptionHandle implements EncryptionHandle{

    @Override
    public String encryption(String param) throws Exception {
        if(StringUtils.isBlank(param)){
            throw new IllegalArgumentException("ThreeDes 加密数据不能为空");
        }
        return ThreeDesUtil.desEncrypt(param);
    }

    @Override
    public String decrypt(String value) throws Exception {
        if(StringUtils.isBlank(value)){
            throw new IllegalArgumentException("ThreeDes 加密数据不能为空");
        }
        return ThreeDesUtil.desDecrypt(value);
    }
}
