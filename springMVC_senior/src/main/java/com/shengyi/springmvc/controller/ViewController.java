package com.shengyi.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringMVC中的视图是view接口，视图的作用是渲染数据，将模型Model中的数据展示给用户
 * SpringMVC中的视图种类有很多，默认有转发视图和重定向视图
 * 当工程中引入jstl的依赖，转发视图就会自动换成jstlView
 * 如果使用的视图技术为Thymeleaf，在SpringMVC的配置文件中配置了thymeleaf的视图解析器，
 * 那么视图解析器解析之后得到的是ThymeleafView
 */
@Controller
public class ViewController {
    /**
     * 测试ThymeleafView
     * 如果返回的视图名称没有任何前缀，那么创建的就是ThymeleafView
     * 当然这是在有Thymeleaf解析器的情况下才会成立
     */
    @RequestMapping("/test/view/thymeleaf")
    public String testThymeleafView(){
        return "success"; // 会被前端控制器解析成ThymeleafView
    }

    /**
     * 测试转发视图 使用的并不多
     * ThymeleafView和InternalResourceView都是通过转发实现的
     * 如果返回的视图名称以forward:开头，那么创建的就是InternalResourceView
     * 这两种视图的区别是
     * InternalResourceView会跳转到指定的页面，最后跳转到的视图还是ThymeleafView
     * 也就是说这中间执行会产生两个视图 InternalResourceView和ThymeleafView
     * 而ThymeleafView会直接跳转到指定的ThymeleafView
     * 地址不发生改变
     */
    @RequestMapping("/test/view/forward")
    public String testInternalResourceView(){
        return "forward:/test/view1";
    }

    /**
     * 测试转发视图 这是转发到的视图
     * 这里的转发视图是直接从服务器中跳转到指定的页面 不会经过前端控制器
     * 也就是用这种方式实现的转发不会对共享数据实现渲染
     */
    @RequestMapping("/test/view1")
    public String testInternalResourceView1(){
        return "success";
    }


    /**
     * 测试重定向视图 RedeirectView
     * 如果返回的视图名称以redirect:开头，那么创建的就是RedeirectView
     * 第一次发送请求到服务器，前端控制器解析访问路径对应的方法，之后发现到视图名称以redirect:开头，就会创建RedeirectView
     * 然后发送重定向，跳转到指定的页面，最后跳转的视图还是ThymeleafView
     * 登录成功使用转发，失败使用重定向
     */
    @RequestMapping("/test/view/redirect")
    public String testRedirectView(){
        return "redirect:/test/view2"; // http://localhost:8080/test/view2
    }

    @RequestMapping("/test/view2")
    public String testRedirectView1(){
        return "success";
    }



}
