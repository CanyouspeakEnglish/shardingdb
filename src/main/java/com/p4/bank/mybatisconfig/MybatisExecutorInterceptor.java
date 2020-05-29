package com.p4.bank.mybatisconfig;

import com.p4.bank.utilnew.MybatisSubmeterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author lzx
 * 执行的拦截器
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
        Object.class,RowBounds.class,ResultHandler.class, CacheKey.class,BoundSql.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class}),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class })})
@Slf4j
@Component
public class MybatisExecutorInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement mappedStatement = (MappedStatement) queryArgs[MybatisSubmeterUtils.MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[MybatisSubmeterUtils.PARAMETER_INDEX];
        final BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();
        String sql = boundSql.getSql();
        SqlParse sqlParse = new SqlParse(parameterObject, sql);
        sql = sqlParse.sqlHandle();
        MappedStatementWraper mappedStatementWraper = new MappedStatementWraper(mappedStatement, boundSql, sql);
        MappedStatement wraper = mappedStatementWraper.wraper();
        queryArgs[MybatisSubmeterUtils.MAPPED_STATEMENT_INDEX] = wraper;
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
