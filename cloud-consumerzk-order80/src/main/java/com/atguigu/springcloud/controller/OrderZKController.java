package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZKController {
    private static final String PAYMENT_URL="http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("consumer/payment/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
        return result;

    }
}
