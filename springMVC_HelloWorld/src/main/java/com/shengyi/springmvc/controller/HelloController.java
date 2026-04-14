package com.shengyi.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 要想让这个类实现接收和响应请求
 * 就必须让它作为Spring的IOC容器中的Controller组件
 * 统一接收请求和发送响应的类，必须添加@Controller注解
 */

@Controller
public class HelloController {

    /**
     * 在idea服务器端中的RequestMapping注解中，可以指定请求的路径
     * 这里的 / 表示的是 localhost:8080/helloworld(上下文路径)/
     *
     * 因为index.html文件是放在WEB-INF目录下的，
     * 所以直接访问是不可取的。这里必须使用请求转发的方式去间接访问。
     * 那么，如果我现在访问localhost:8080/helloworld 访问不到
     * 就需要添加一个方法，返回一个字符串，
     * 这个字符串就是index.html文件的逻辑视图（去除前后缀）
     * @return
     */

    @RequestMapping("/")
    public String protal(){
        /**
         * 返回一个字符串，这个字符串就是index.html文件的逻辑视图
         * 然后会被SpringMVC-servlet.xml文件中定义的视图解析器解析
         * 就会找到index.html文件，并返回给浏览器
         */
        System.out.println("进入首页");
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("登录成功");
        return "success";
    }
}

