package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/configInfo")
    public String configInfo() {
        return "configInfo: " + configInfo +"\t\tserverport:"+serverPort;
    }
}
