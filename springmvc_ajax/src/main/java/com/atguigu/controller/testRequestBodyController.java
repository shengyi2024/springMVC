package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// @RequestBody: 获取请求体中的json格式的数据
// @RestController: 用于标识一个控制器类，相当于为该类添加了一个@Controller注解，并为其中的每一个方法添加了@ResponseBody注解
@Controller
public class testRequestBodyController {

//    @RequestMapping("/test/ajax")
//    public void testAjax(Integer id, HttpServletResponse response,
//                         @RequestBody String requestBody // 转换成String字符串
//    ) throws IOException {
//        // 获取请求体中的数据
//        System.out.println("requestBody:"+requestBody);
//        // 获取id
//        System.out.println("id:"+id);
//        // 响应数据
//        response.getWriter().write("Hello,Ajax&Axios");
//    }

    // 无论是在url中的数据，还是post请求中的数据，对于key=value形式的数据，可以直接使用同名的形参获取
    // 对于在请求体中的json格式的数据，则需要使用@RequestBody注解
    // 获取到请求体的数据类型是字符串类型 显然我并不需要这样的数据 我希望使用对象或者map集合封装数据
    // 第一步就需要解析json格式的字符串 那么就需要引入jackson的依赖包
    // 第二步需要在springmvc的配置文件中开启注解驱动
    // 第三步使用@RequestBody注解标识在形参位置 根据形参类型会自动封装成对应java对象

//    public void testAjax(Integer id, HttpServletResponse response,
//                         @RequestBody User user) throws IOException{
//        // System.out.println("username:"+ username+",password:"+password); // "username=admin&password=123456" 可以获取到keyey=value格式的数据
//        System.out.println("id:"+id);
//        System.out.println("user:"+user);
//        response.getWriter().write("Hello,Ajax&Axios");
//
//    }
    @RequestMapping("/test/ajax")
    public void testAjax(Integer id, HttpServletResponse response,
                         @RequestBody Map<String,Object> map) throws IOException{
        System.out.println("id:"+id);
        System.out.println("map:"+map);
        response.getWriter().write("Hello,Ajax&Axios");
    }
}
