package com.shengyi.springmvc.controller;

import com.shengyi.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 访问用户资源 路径统一以/user开头
 * 1.查询所有的用户信息 get请求方式 /user
 * 2.根据id查询用户信息 get请求方式 /user/{id}
 * 3.添加用户信息 post请求方式 /user
 * 4.修改用户信息 put请求方式 /user
 * 5.删除用户信息 delete请求方式 /user/{id}
 */
@Controller
public class TestRestController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUser(){
        System.out.println("查询所有的用户信息");
        // 查询数据库 传递到模型层
        return "success";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable("id")Integer id){
        System.out.println("查询id为"+id+"的用户信息");
        return "success";
    }
    /**
     * @RequestMapping(value = "/user", method = RequestMethod.GET)
     * @RequestMapping(value = "/user", method = RequestMethod.POST)
     * 请求路径是一致的，但是请求方式不一致，使得处理发送的请求的方法不一样
     * 相当于寻路，路径是哪一条街道，而方法是哪一户人家
     *
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(User user){
        System.out.println("添加用户信息");
        System.out.println("用户名:"+user.getUsername()+",密码:"+user.getPassword());
        return "success";
    }

    /**
     * 如果只是设置method="put"，RESTful无法匹配对应方法
     * 因为表单提交方式只用两种，get和post，所以当前发送过来的请求被getAllUser()方法处理了，
     * 而不是updateUser()方法
     *
     * 现在提出一种解决方案：设置过滤器，将put请求交给updateUser()方法处理
     * 1.在web.xml中配置过滤器 注意这个过滤器必须配置在解码字符的过滤器之后
     * 2.form表单中设置method属性值为post
     * 3.在表单中配置隐藏输入 <input type="hidden" name="_method" value="put">
     *     其中要保证name属性值必须为"_method"，而value属性可以设置为put或delete
     *
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(User user){
        System.out.println("修改用户信息");
        System.out.println("用户名:"+user.getUsername()+",密码:"+user.getPassword());
        return "success";
    }

    /**
     * 方法同上 只是要获取id来删除数据
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id")Integer id){
        System.out.println("删除id为"+id+"的用户信息");
        return "success";
    }

}

/**
 * 1. On 'Update' action (执行更新操作时)
 * 当前值: Redeploy (重新部署)
 * 含义: 当您手动触发更新操作时，会重新部署整个应用到 Tomcat
 * 触发方式: 点击工具栏的 Update 按钮或使用快捷键
 * 2. On frame deactivation (窗口失去焦点时)
 * 当前值: Update classes and resources (更新类和资源)
 * 含义: 当 IDEA 窗口失去焦点（切换到其他应用）时，自动更新修改过的类和资源文件到 Tomcat
 * 效果: 修改代码后，只需切换到浏览器刷新即可看到效果，无需手动重启
 */
