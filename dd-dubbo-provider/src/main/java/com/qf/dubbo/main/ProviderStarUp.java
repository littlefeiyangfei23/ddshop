package com.qf.dubbo.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;


//用来测试用的
public class ProviderStarUp {
    public static void main(String[] args)  throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:spring/spring-dubbo-provider.xml"});
        context.start();
        System.in.read(); // press any key to exit//这里停住了
    }

}
