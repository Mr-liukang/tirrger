package com.liukang.tirrger.main;


import com.liukang.tirrger.intercept.MyInterceptor;
import com.liukang.tirrger.proxy.ProxyBean;
import com.liukang.tirrger.service.HelloService;
import com.liukang.tirrger.service.impl.HelloServiceImpl;


public class AopMain {

	public static void main(String[] args) {
		testProxy();
	}
	
	private static void testProxy() {
		HelloService helloService = new HelloServiceImpl();
		HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
		proxy.sayHello("zhangsan");
		System.out.println("\n###############name is null!!#############\n");
		proxy.sayHello(null);
	}

}
