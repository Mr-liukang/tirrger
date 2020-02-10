package com.liukang.tirrger;


import com.liukang.tirrger.interceptor.Intercept1;
import com.liukang.tirrger.interceptor.MulitiIntercept1;
import com.liukang.tirrger.interceptor.MulitiIntercept2;
import com.liukang.tirrger.interceptor.MulitiIntercept3;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//@SpringBootApplication(scanBasePackages = {"com.liukang.tirrger.aspect"})
@SpringBootApplication
@MapperScan("com.liukang.tirrger.dao")
@EnableCaching
@Configuration
public class TirrgerApplication extends WebSecurityConfigurerAdapter  {
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private MessageListener messageListener;
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler =null;
    @Autowired
    private DataSource dataSource = null;
    @Autowired
    private UserDetailsService userDetailsService;
    @Value("${system.user.password.secret}")
    private String secret ;
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
    //@Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration ir1 = registry.addInterceptor(new MulitiIntercept1());
        InterceptorRegistration ir2 = registry.addInterceptor(new MulitiIntercept2());
        InterceptorRegistration ir3 = registry.addInterceptor(new MulitiIntercept3());
        ir1.addPathPatterns("/interceptor/*");
        ir2.addPathPatterns("/interceptor/*");
        ir3.addPathPatterns("/interceptor/*");
    }
   /* @Override
    protected void  configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwdQuery = "select user_name,pwd,available from t_user where user_name = ?";
        String roleQuery = "SELECT a.USER_NAME,b.id\n" +
                "from \n" +
                "t_user a LEFT JOIN t_user_role b on a.id = b.user_id\n" +
                "where a.user_name = ?";
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(pwdQuery)
                 .authoritiesByUsernameQuery(roleQuery);

    }*/
   @Override
   protected void  configure(AuthenticationManagerBuilder auth) throws Exception {
       PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       auth.userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder);

   }
    /*@Override
    protected void  configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/table").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll().and().formLogin().and().httpBasic();

    }*/
    @Override
    protected void  configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login/page")
                .defaultSuccessUrl("/admin/welcome")
                .and().authorizeRequests()
                .antMatchers("/user/**")
                .access("hasRole('ADMIN')")
                .and()
                .rememberMe()
                .tokenValiditySeconds(10).key("remember-me-key")
                .and().httpBasic()
                .and().authorizeRequests().antMatchers("/admin/**").permitAll()

                .and()
                .logout().logoutUrl("/logout/page")
                .logoutSuccessUrl("/login/page");

    }
}
