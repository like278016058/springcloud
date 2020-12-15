package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "")
public class OrderController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/consumer/payment/hystirx/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") String id){
        return paymentService.paymentInfo_OK(id);
    }
    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    })
    @GetMapping("/consumer/payment/hystirx/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") String id){
        return paymentService.paymentInfo_Timeout(id);
    }
    public String paymentTimeOutHandler(@PathVariable("id") String id){
        return "消费者兜底啦";
    }
    public String fallbackHandler(@PathVariable("id") String id){
        return "消费者统一兜底啦";
    }
}
