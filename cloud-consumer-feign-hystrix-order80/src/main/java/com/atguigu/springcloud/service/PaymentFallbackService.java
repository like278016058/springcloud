package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String paymentInfo_OK(String id) {
        return "paymentfallbackservice  fallback-paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(String id) {
        return "paymentfallbackservice  fallback-paymentInfo_Timeout";
    }
}
