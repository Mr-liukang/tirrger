package com.liukang.tirrger.utils;

public class ProxTest {
    public static void main(String[] args) {
        User user = new FisrtUser();
        new ProxyUser(user).userProxy();
    }
}
