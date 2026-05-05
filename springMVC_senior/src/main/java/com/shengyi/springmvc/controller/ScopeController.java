package com.shengyi.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 测试向会话域和应用域共享数据
 * Session  Application
 * Request的生命周期是请求开始到请求结束之间
 * Session的生命周期是开启浏览器到关闭浏览器之间
 * Application的生命周期是开启服务器到关闭服务器之间
 *
 * 注意:如果浏览器没有关闭，但是服务器重新部署了，这个时候Session共享的数据不会丢失，
 * 但是Application共享的数据会丢失
 */

/**
 * 观察到的结果是
 * 访问第一个路径，出现a，1.
 * 访问第二个路径，出现b，2.
 * 访问第三个路径，出现b，c，2，3.
 */
@Controller
public class ScopeController {

    @RequestMapping("/test/request")
    public String testRequest(HttpServletRequest request){
        request.setAttribute("u1","a");
        request.setAttribute("p1","1");
        return "scope";
    }

    @RequestMapping("/test/session")
    public String testSession(HttpSession session){
        session.setAttribute("u2","b");
        session.setAttribute("p2","2");
        return "scope";
    }

    @RequestMapping("/test/application")
    public String testApplication(HttpSession session){
        ServletContext context = session.getServletContext();
        context.setAttribute("u3","c");
        context.setAttribute("p3","3");
        return "scope";
    }
}
