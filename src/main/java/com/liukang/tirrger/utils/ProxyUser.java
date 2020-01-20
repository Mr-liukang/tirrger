package com.liukang.tirrger.utils;

public class ProxyUser {
    private User user ;

    public ProxyUser(User user) {
        this.user = user;
    }
    public void userProxy(){
        System.out.println("=======");
        user.sayHello();
        System.out.println("-------");
    }

}
