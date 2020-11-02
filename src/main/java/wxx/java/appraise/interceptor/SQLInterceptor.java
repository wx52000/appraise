package wxx.java.appraise.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class SQLInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SQLInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameterMap = null;
        if (invocation.getArgs().length > 1) {
            parameterMap = invocation.getArgs()[1];
        }
        String mapperID = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameterMap);
        String sql = boundSql.getSql();

        StringBuffer sb = new StringBuffer();
        sb.append("sql语句:\n").append("====>   mapperID: " + mapperID + "\n")
                .append("====>        sql: " + sql.replaceAll("\\\n", " ").replaceAll("\\s+", " ") + "\n")
                .append("====>     params: " + parameterMap + "\n");
        logger.debug(sb.toString());
        return invocation.proceed();
    }

    //获取代理对象
    public Object plugin(Object object) {
        //生成object对象的动态代理对象
        return Plugin.wrap(object, this);
    }

    //设置代理对象的参数
    public void setProperties(Properties properties) {

    }
}
