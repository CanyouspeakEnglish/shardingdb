package com.p4.bank.utilnew;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author lzx
 * 分表对外调用
 */
public class SubmeterService {

    /**
     * 范围查询表名
     * @param tableName
     * @param submeterType
     * @param dates
     * @return
     */
    public static List<String> submeter(String tableName
            , SubmeterType submeterType, Date... dates){
        return SubmeterUtils.submeter(tableName,submeterType,dates);
    }

    /**
     * 固定日期
     * @param tableName
     * @param submeterType
     * @param dates
     * @return
     */
    public static String submeter(String tableName
            , SubmeterType submeterType, Date dates){
        List<String> submeter = SubmeterUtils.submeter(tableName, submeterType, dates);
        if(CollectionUtils.isEmpty(submeter)){
            return null;
        }
        return submeter.get(0);
    }

    /**
     * 数据取模
     * @param tableName
     * @param submeterType
     * @param source
     * @param delivery
     * @return
     */
    public static String delivery(String tableName
            , SubmeterType submeterType,Object source,Integer delivery){

        if(ObjectUtils.isEmpty(source)){
            throw new NullPointerException("delivery source is empty");
        }
        if(ObjectUtils.isEmpty(delivery)){
            throw new NullPointerException("delivery delivery is empty");
        }
        BigInteger sourceInteger = BigInteger.valueOf(Long.valueOf(source.toString()));
        BigInteger deliveryBig = new BigInteger(delivery.toString());
        BigInteger[] integers ={sourceInteger,deliveryBig};
        List<String> submeter = SubmeterUtils.submeter(tableName, submeterType, integers);
        if(CollectionUtils.isEmpty(submeter)){
            return null;
        }

        return submeter.get(0);
    }

    public static void main(String[] args) {
        for (int i=0;i<1000 ; i++){
            String string = "6214686001913891"+i;
            String test = SubmeterService.delivery("test", SubmeterType.REMAINDER, string, 12);
            System.out.println("源"+string+"表名"+test);
        }


    }
}
