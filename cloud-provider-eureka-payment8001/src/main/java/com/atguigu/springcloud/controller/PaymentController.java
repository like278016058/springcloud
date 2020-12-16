package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    //服务发现Discovery
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果{}",result);
        if (result>0){
            return new CommonResult(200,"插入数据成功",result);
        }else{
            return new CommonResult(400,"插入数据失败");
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果{}",payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(400,"查询失败,serverPort:"+serverPort);
        }
    }
    //服务发现Discovery
    @GetMapping(value = "/payment/discovery")
    public Object getDiscovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("----------element:" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("serviceId:[{}], 主机名称:[{}], 端口号:[{}], url地址:[{}]", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }
        return this.discoveryClient;
    }
    /**
     * feign超时测试
     *
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    /**
     * P94用,sleuth+zipkin链路监控使用
     *
     * @return
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi,I am payment_zipkin server fall back, welcome to";
    }


}
