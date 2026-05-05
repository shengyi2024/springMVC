package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试xml配置异常处理器
 */
@Controller
public class TestXMLExceptionResolverController {

    @RequestMapping("/testHello")
    public String testHello(){
        System.out.println(1/0); // 出现算术异常
        return "success";
    }
}
