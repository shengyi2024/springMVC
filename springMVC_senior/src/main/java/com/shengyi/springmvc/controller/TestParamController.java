package com.shengyi.springmvc.controller;

import com.shengyi.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class TestParamController {
    /**
     * 通过servletAPI获取请求参数 HttpServletRequest 不常用
     * 因为springMVC封装了servlet对象，可以直接调用对应方法获取请求参数
     * 问:如何获取请求参数?
     * 答:可以在控制器方法形参中添加HttpServletRequest对象 以此获取请求参数
     * 问:怎样实现的过程?
     * 答:简单来说，就是浏览器向服务器发送请求，请求体中包含请求参数
     *    而我们定义的前端控制器接收请求的路径，
     *    然后在管理的Controller组件中的方法中去找对路径的方法，
     *    然后检测方法中是否有参数以及参数的类型，
     *    根据参数为HttpServletRequest进行赋值，
     *    以此实现通过HttpServletRequest获取请求参数
     */
    @RequestMapping("/param/servletAPI")
    public String getParamByServletAPI(HttpServletRequest req){
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:"+username+" password:"+password);
        return "success";
    }

    /**
     * 通过控制器方法形参获取请求参数
     * 只需要保证表单中的name属性与形参的属性名一致即可
     * 问:如果形参的属性名不一致,如何获取参数?
     * 答:可以在形参前添加@RequestParam(name)注解，设置映射关系
     * required 属性:设置属性是否必须传输value对应的请求参数
     *          http://localhost:8080/param?password=123
     *          访问这个路径会报错（HTTP状态 400 - 错误的请求），
     *          因为没有传递username参数 默认为true
     * defaultValue 属性:设置属性的默认值 required属性为true或为false都可以使用
     *
     * 补充注解解释:
     * @RequestParam: 将请求参数和方法中的形参绑定
     * @RequestHeader: 将请求头信息和方法中的形参绑定
     * @CookieValue: 将Cookie数据和方法中的形参绑定
     * 这三个注解都有相同的注解属性值：required,value,defaultValue
     * 默认情况下，控制器方法中的形参前面没有添加注解，是将匹配形参名为请求参数的名称进行映射
     * 所以想要获取请求头信息和Cookie数据，不能只写形参（默认是将它与请求参数匹配），要加上对应的注解
     *
     * @RequestHeader("referer") String referer
     * referer http://localhost:8080/
     * RequestHeader:referer:http://localhost:8080/
     */

    @RequestMapping("/param")
    public String getParam(@RequestParam(value = "username", required = true, defaultValue = "codedog") String username,
                           String password,
                           @RequestHeader(value = "referer",required = false, defaultValue = "http://localhost:8080/") String referer,
                           @CookieValue("JSESSIONID") String jsessionId
    ){

        System.out.println("CookieValue:JSESSIONID:"+jsessionId);
        System.out.println("RequestHeader:referer:"+referer);
        System.out.println("username:"+username+" password:"+password);
        return "success";
    }

    /**
     * 通过pojo获取请求参数
     *
     * 如果浏览器发送的请求信息特别多怎么处理比较好呢？
     * 将请求参数封装到实体类中
     *
     * 在下面这个例子中:
     *      表单提交的数据有username和password
     *      User类中有username和password属性
     *      属性名对应一致，就会自动匹配赋值
     */
    @RequestMapping("/param/pojo")
    public String getParamByPojo(User user){
        System.out.println(user.getUsername()+":"+user.getPassword());
        return "success";
    }

    /**
     * 处理请求参数的乱码问题:
     *      1.为什么不通过在方法中的HttpServletRequest对象中设置字符集解决乱码问题?
     *          因为这个形参已经获取了乱码的请求参数，之后再修改已经无效了。
     *      2.原来的Servlet中为什么可以修改呢?
     *          因为原来在学习javaweb时，我们创建的Servlet实现类中service方法的形参是HttpServletRequest对象
     *          我们使用的request.setCharacterEncoding("utf-8")必须在第一次读取任何请求参数之前调用，
     *          一旦调用了 getParameter()、getReader() 或 getInputStream() 等方法，再设置字符集就无效了
     *      3.所以合适的解决方法是什么?
     *      使用 Spring MVC 提供的 CharacterEncodingFilter 过滤器
     *      在 web.xml 中配置该过滤器，确保它在所有 Servlet 之前执行
     *      这样可以在请求到达你的控制器之前就正确设置字符集
     *      4.表单提交方式如果是post，会出现乱码问题；但是get方式不会。为什么呢？
     *      在tomcat9自带的web.xml配置中
     *      <Connector port="8080" protocol="HTTP/1.1"
     *          connectionTimeout="20000"
     *          redirectPort="8443"
     *          maxParameterCount="1000"
     * 			URIEndcoding="UTF-8"/>
     * 	    添加了URIEndcoding="UTF-8"，使得get请求对于中文字符没有乱码.
     *
     */
    @RequestMapping("/param/rm")
    public String getParamRM(String  username, String password){
        System.out.println("username:"+username+" password:"+password);
        return "success";
    }
}
