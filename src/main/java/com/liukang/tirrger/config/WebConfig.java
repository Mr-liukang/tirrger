package com.liukang.tirrger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
       registry.addViewController("/login/page").setViewName("login");
       registry.addViewController("/logout/page").setViewName("logout_welcome");
        registry.addViewController("/admin/welcome").setViewName("/admin/welcome");
       registry.addViewController("logout").setViewName("logout");

    }
}
