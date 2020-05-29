package com.p4.bank.mybatisconfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author lzx
 * 执行的拦截器
 */
@Intercepts(
        {@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class})})
@Slf4j
@Component
public class MybatisResultSetInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        DataDecryptionProcess dataDecryptionProcess = new DataDecryptionProcess(proceed);
        return dataDecryptionProcess.process();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
