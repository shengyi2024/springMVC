package com.atguigu.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置拦截器类
 * 1.创建拦截器类 实现HandlerInterceptor接口
 * 2.重写默认方法
 * 3.配置拦截器 使其发挥作用 核心标签:<mvc:interceptors>
 *      三种方式配置拦截器:
 *        1.<mvc:interceptors>
 *              <bean class="com.atguigu.interceptor.HelloInterceptor"></bean>
 *          </mvc:interceptors>
 *          直接在标签内创建一个拦截器的bean，交给IOC容器进行管理
 *        2.<mvc:interceptor>
 *            <ref bean="helloInterceptor"/>
 *          </mvc:interceptor>
 *          事先准备好一个bean，设置好相应的id属性，通过ref引用，配置拦截器
 *        3.使用@Component注解创建一个bean，交给IOC容器进行管理，要保证拦截器bean会被扫描到。
 *        再进行引用即可。
 *        <mvc:interceptors>
 *            <ref bean="helloInterceptor"/>
 *        </mvc:interceptors>
 *        bean和ref标签配置的拦截器默认对DispatcherServlet处理的所有请求进行拦截。
 *        不管当前的请求路径是否存在，拦截器都会执行
 *        4.<mvc:interceptor>
 *            <mvc:mapping path="/*"/> 表示拦截的请求路径 /* 表示一层路径的请求 /** 表示多层路径的请求
 *            <mvc:exclude-mapping path="/"/> 表示不拦截的请求路径
 *            <ref bean="helloInterceptor"/> 表示引用的bean 使用哪个拦截器
 *          </mvc:interceptor>
 *
 *  多个拦截器的执行顺序与配置顺序有关
 *  PreHandle()按照配置的顺序执行
 *  PostHandle(),afterCompletion()按照配置的倒序执行
 *
 *  在存在多个拦截器时，若有一个拦截器的Prehandle()方法返回false,则后续的拦截器preHandle()方法不执行，
 *  并且所有拦截器的PostHandle()方法也不执行
 *  但是只有在这个拦截器之前的拦截器的afterCompletion()会执行
 *
 */
@Component
public class HelloInterceptor implements HandlerInterceptor {
    // 在控制器方法执行之前 执行此方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("HelloInterceptor:preHandle()");
        // 返回值为true，表示放行；为false，表示拦截。
        return true;
    }

    // 在控制器方法执行之后，视图渲染之前 执行此方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("HelloInterceptor:postHandle()");
    }

    // 在视图渲染之后 执行此方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("HelloInterceptor:afterCompletion()");
    }
}
