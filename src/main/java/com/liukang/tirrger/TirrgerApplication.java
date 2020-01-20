package com.liukang.tirrger;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

//@SpringBootApplication(scanBasePackages = {"com.liukang.tirrger.aspect"})
@SpringBootApplication
@MapperScan("com.liukang.tirrger.dao")
public class TirrgerApplication {
    @Autowired
    private RedisTemplate redisTemplate = null;
    public static void main(String[] args) {
        SpringApplication.run(TirrgerApplication.class, args);
    }
    /*@Bean
    public MyAspect initMyAspect(){
        return new MyAspect();
    }*/
    @PostConstruct
    public void init(){
        initRedisTemplate();
    }
    private void initRedisTemplate(){
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }
}
