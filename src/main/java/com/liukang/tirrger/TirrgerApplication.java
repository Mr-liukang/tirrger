package com.liukang.tirrger;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


import javax.annotation.PostConstruct;

//@SpringBootApplication(scanBasePackages = {"com.liukang.tirrger.aspect"})
@SpringBootApplication
@MapperScan("com.liukang.tirrger.dao")
public class TirrgerApplication {
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private MessageListener messageListener;
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler =null;
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
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if(taskScheduler != null){
         return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setTaskExecutor(initTaskScheduler());
        Topic topic = new ChannelTopic("topic1");
        container.addMessageListener(messageListener,topic);
        return container;

    }
}
