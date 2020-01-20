package com.liukang.tirrger.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.liukang.tirrger.pojo")
public class AppConfig {
  /*  @Bean(name = "user")
    public User initUser(){
        User user = new User();
        user.setId((long) 1);
        user.setNote("l");
        user.setUserName("username");
        return  user;
    }*/
/*  @Bean
  public DataSource getDataSource(){
      Properties props = new Properties();
      props.setProperty("driver","oracle.jdbc.OracleDriver");
      props.setProperty("url","jdbc:oracle:thin:@localhost:1521:XE");
      props.setProperty("user","liukang");
      props.setProperty("password","liukang");
      DataSource dataSource = null;
      try {
          dataSource = BasicDataSourceFactory.createDataSource(props);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return  dataSource;
  }*/
}
