package com.liukang.tirrger.config;


import com.liukang.tirrger.pojo.DataBaseProperties;
import com.liukang.tirrger.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class IocTest {
    private static Logger logger = Logger.getLogger("IocTest");

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        DataBaseProperties dp = ctx.getBean(DataBaseProperties.class);
        System.out.println(dp.getDriverName());
        logger.info(""+user.getId());
    }
}
