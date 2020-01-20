package com.liukang.tirrger.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;
@Intercepts({
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})
})
public class MyPlugin  implements Interceptor {
    Properties properties;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截到的方法");
        System.out.println(properties.getProperty("k1"));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
