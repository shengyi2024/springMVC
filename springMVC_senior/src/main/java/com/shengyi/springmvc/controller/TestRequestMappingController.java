package com.shengyi.springmvc.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 测试RequestMapping注解
 * @RequestMapping 位置:
 *      1.@Controller类上:
 *            表示这个类中的方法都需要经过这个路径才能访问
 *      2.@Controller方法上:
 *          表示类中的具体方法的请求路径
 *      这就像是mybatis中的namespace和mapper一样
 *      类上的@RequestMapping告诉你请求得到哪个类上进行处理
 *      而方法上的@RequestMapping告诉你具体要到哪个方法上处理
 *
 *      这种方法的好处就是避免不同的类的相同访问路径冲突
 *
 * @RequestMapping 属性值:
 *      1.value:@AliasFor("path") 表示value属性的别名是path
 *              String[] value() default {}; value属性是数组类型，
 *              也就是说，注解中的value属性值可以有多个。
 *              控制器方法可以处理多个请求。
 *              (Servlet也可以设置多个url-pattern达到映射多个请求路径)
 *      2.mathod:RequestMethod[] method() default {};
 *              表示的是请求方式，默认是所有方式都支持
 *              类型是数组类型，也就是说，可以设置多个请求方式
 *              若表单提交方式设置为post，可以通过method属性值为RequestMethod.GET
 *              若直接通过地址发送请求是get请求。
 *              举个例子:
 *              如果我现在设置提交方法为POST。
 *              如果我直接通过http://localhost:8080/hello发送请求，
 *              想要去访问success.html,就会报405错误。
 *              如果通过表单提交，action属性设置为/hello，
 *              则可以成功访问success.html
 *       3.在@RequestMapping的基础上，结合请求方式的一些派生注解:
 *          @GetMapping,@PostMapping,@PutMapping,@DeleteMapping
 *          省略请求方式的设置，则注解名直接表示对应的请求方式
 *          这个有点类似组件注解，在spring的IOC容器中，对于三层架构的注解，
 *          有@Controller,@Service,@Repository，
 *          而对于一般的类使用的是@Component，
 *          这里的@RequestMapping注解就有点类似@Component，具有一般性，什么控制都可以用
 *          对于指定的提交方式的类或者方法，可以使用派生注解 类似三层架构中的注解
 *      4.params:String[] params() default {}; 跳过
 *      5.headers:String[] headers() default {}; 跳过
 *
 *      SpringMVC支持ant风格的路径:
 *          简单来说，就是使用一些特殊的符号来表示一些路径的匹配 有点像mysql里的模糊查询
 *          特殊符号常用的有一下几个:
 *          1.? : 表示任意的单个字符
 *          2.* : 表示任意的0个或多个字符
 *          3.** : 表示任意层数的任意目录
 *          注意: 在使用 ** 的时候 只能使用 / ** /xxx的方式
 *          那这个东西在哪里使用呢?
 *          是在服务器中的表示请求路径时使用还是在浏览器的发送请求的时候使用呢?
 *          @RequestMapping("/a?a/test/ant")
 *          可见使用在服务器中的表示请求路径的时候使用
 *
 *          注意:
 *          这三个字符不能使用 ?
 *          因为会与路径与参数的连接的? 冲突 那就会认为?后面的是key=value形式的参数
 *          以及 / 因为会被识别成路径的分隔符
 *
 *     SpringMVC支持路径中的占位符:
 *          传统格式: /hello/test?id=1;
 *          REST格式: /hello/test/1;
 *          也就是说，我们把原来放在?之后的key=value形式的参数以一个路径的方式传递过去
 *          那么我在当前请求路径下如何获取参数呢?
 *          @RequestMapping("/test/rest/{id}")
 *          通过{key}的方式作为占位符，获取请求中的参数
 *
 *          @RequestMapping("/test/rest/{id}")
 *          public String testRest(@PathVariable("id") Integer id){
 *               // 获取请求的参数
 *              return "success";
 *          }
 *          解析一下这个方法:
 *          首先我们在@RequestMapping中设置的value属性值中添加了一个{key}形式的路径
 *          相当于原来我们传递参数的时候的?之后的key=value属性值
 *          然后在方法中添加一个形参，在形参的前面添加@PathVariable("key")
 *          这里就是指定我们在路径中设置的占位符的key的值赋值给我们定义的形参
 *
 *     SpringMVC获取请求参数:
 *
 */
@Controller
//@RequestMapping("/test")
public class TestRequestMappingController {

    @RequestMapping(
            value = {"/hello","/abc"},
            method = {RequestMethod.GET,RequestMethod.POST})
    public String hello(){
        return "success";
        // 访问 http://localhost:8080/test/hello
    }
    @RequestMapping("/**/test/ant")
    public String testAnt(){
        return "success";
    }

    @RequestMapping("/test/rest/{username}/{id}")
    public String testRest(@PathVariable("id") Integer id,@PathVariable("username")String  username){
        // 获取请求的参数
        System.out.println("id:"+id+",username:"+ username);
        return "success";
    }
}
