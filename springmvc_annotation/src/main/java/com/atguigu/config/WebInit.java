package com.atguigu.config;

import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 在servlet3.0中，容器会在类路径中查找实现javax.servlet.ServletContainerInitializer接口的类，
 * 如果找到的话，就用它来配置Servlet容器（即tomcat），否则，就使用web.xml配置。
 * web.xml的作用就是配置Servlet容器 （即tomcat）
 * springmvc.xml的作用是用来配置springmvc框架
 * WebInit类是用来替代web.xml，来配置Servlet容器
 *
 * 在Spring中提供了ServletContainerInitializer接口的实现，名为SpringServletContainerInitializer，
 * 这个类反过来又会查找实现WebApplicationInitializer的类并将配置的任务交给它们来完成。
 * Spring3.2引入了一个便利的WebApplicationInitializer基础实现，名为AbstractAnnotationConfigDispatcherServletInitializer，
 * 当我们的类拓展了AbstractAnnotationConfigDispatcherServletInitializer并将部署到了Servlet3.0容器的时候，
 * 容器就会自动发现它，并用来配置Servlet上下文。
 *
 * 简单来说，Spring提供了ServletContainerInitializer接口的实现，名为SpringServletContainerInitializer，
 * 然后这个类会找到实现WebApplicationInitializer的类，将配置任务交给这些类来完成。
 * 在Spring3.2中，提供了WebApplicationInitializer的基本实现，名为AbstractAnnotationConfigDispatcherServletInitializer，
 * 于是我们只需要继承这个类的实现，tomcat就会自动发现，并使用它来配置Servlet上下文。
 * Servlet上下文表示的是Servlet容器的全局配置。实现在启动服务器到销毁服务器之间的数据共享。
 */
// 使用WebInit类替代web.xml 实现配置Servlet容器
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 创建一个配置类代替Spring的配置文件
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    // 创建一个配置类代替SpringMVC的配置文件
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // 设置前端控制DispatcherServlet的url-pattern 需要处理的请求路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // 配置过滤器
    @Nullable
    @Override
    protected Filter[] getServletFilters() {
        // 创建编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceResponseEncoding(true);
        // 创建处理请求方式的过滤器
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter};
    }
}
