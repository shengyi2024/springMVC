package com.atguigu.controller;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ResponseBody注解用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器上
 * 一般来说响应到浏览器的数据是java对象 但是在服务器的控制器方法上返回的是字符串
 * 所以我们需要将我们的java对象转成json格式的字符串再返回到我们的浏览器上
 * 使用前提:
 *  1.导入jackson依赖包 2.在springmvc.xml的配置文件中设置注解驱动
 *  3.将需要返回的json字符串的java对象直接作为控制器方法的返回值，使用@ResponseBody注解标识方法
 *  就可以将java对象自动地转换成json字符串，并作为响应体返回给浏览器
 *  常见地java对象转换为json的结果: 实体类-->json对象 map-->json对象 list-->json数组
 */
// @RestController
// 是一个复合注解 标识在控制器的类上 就相当于给类添加了@Controller，并为类中的每个方法添加了@ResponseBody注解
@Controller
public class testResponseController {

    @RequestMapping("/test/ResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "success";
    }

    @RequestMapping("/test/ResponseBody/json")
    @ResponseBody
    public User testResponseBodyJson(){
        User user = new User("张三","123456",23,"男");
        return user;
    }

//    @RequestMapping("/test/ResponseBody/json")
//    @ResponseBody
//    public Map<String,Object> testResponseBodyJson(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("username","李四");
//        map.put("password","123456");
//        map.put("age",23);
//        map.put("gender","女");
//        return map;
//    }

//    @RequestMapping("/test/ResponseBody/json")
//    @ResponseBody
//    public List testResponseBodyJson(){
//        List list = new ArrayList<>();
//        list.add("张三");
//        list.add("123456");
//        list.add(23);
//        list.add("女");
//        return list;
//    }

}
