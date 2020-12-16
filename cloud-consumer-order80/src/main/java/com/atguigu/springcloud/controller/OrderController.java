package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    //private static final String PAYMENT_URL="http://localhost:8001";//单机版
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";//集群版
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping("consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    /**
     * P94用,sleuth+zipkin链路监控使用
     *
     * @return
     */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin/", String.class);
        return result;
    }
}
