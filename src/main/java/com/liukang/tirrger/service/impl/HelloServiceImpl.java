package com.liukang.tirrger.service.impl;

import com.liukang.tirrger.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if(name == null){
            throw new RuntimeException("出现异常");
        }
        System.out.println("sayHello "+name);
    }
}
