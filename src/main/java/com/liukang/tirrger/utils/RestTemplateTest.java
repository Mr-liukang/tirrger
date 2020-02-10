package com.liukang.tirrger.utils;

import com.liukang.tirrger.pojo.User;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
    public static void main(String[] args) {
        RestTemplate rt = new RestTemplate();
        User user = rt.getForObject("http://localhost:8090/user/{id}",User.class,1);
        System.out.println(user.getUserName());
    }
}
