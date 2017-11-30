package com.qf.dubbo.main;

import com.qf.dubbo.service.CustomerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//测试用的
public class ConsumerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring/spring-dubbo-consumer.xml"});
        context.start();
        CustomerService customerService = (CustomerService)context.getBean("demoService"); // obtain proxy object for remote invocation
        String words = customerService.say("hello");// execute remote invocation
        System.out.println(words); // show the result
    }
}
