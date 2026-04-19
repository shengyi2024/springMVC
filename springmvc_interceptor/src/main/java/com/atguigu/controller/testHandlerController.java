package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试拦截器的控制器
 */
@Controller
public class testHandlerController {

    // 测试模块是否能够正常运行
    @RequestMapping("/test/hello")
    public String testHello(){
        return "success";
    }
}
