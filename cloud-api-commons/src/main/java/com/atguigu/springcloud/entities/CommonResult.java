package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T>{
    private Integer status;
    private String message;
    private T data;

    public CommonResult(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data=null;
    }
}
