package com.shengyi.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 测试通过不同方式向域对象中共享数据
 * 1.方法一:使用ServletAPI向request域对象共享数据 不推荐
 * 2.方式二:使用ModelAndView向request域对象共享数据 官方推荐
 * 3.方式三:使用Model向request域对象共享数据 Model对象自动创建
 * 4.方式四:使用ModelMap向request域对象共享数据 与Model的区别
 * 5.方式五:使用map向request域对象共享数据
 *
 * 方法三四五使用的都是 BindingAwareModelMap
 * 继承关系:
 * Object
 * AbstractMap
 * HashMap
 * LinkedHashMap (implements Map(K,V))
 * ModelMap
 * ExtendedModelMap (implements Model)
 * BindingAwareModelMap
 */
@Controller
public class RequestController {

    @RequestMapping("/byServletAPI")
    public String byServletAPI(HttpServletRequest request){
        request.setAttribute("username","shengyi");
        request.setAttribute("password","123456");
        return "answer";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        /**
         * ModelAndView实现了两个功能:
         *      1.向request域对象共享数据
         *      2.设置逻辑视图,实现视图跳转
         * 前端控制器的底层会自动地将共享数据和视图资源放入ModelAndView对象中
         */
        ModelAndView mav = new ModelAndView();
        mav.addObject("username","shengyi");
        mav.addObject("password","123456");
        mav.setViewName("answer");
        return mav;
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){
        System.out.println(model.getClass().getName());
        // org.springframework.validation.support.BindingAwareModelMap
        model.addAttribute("username","shengyi");
        model.addAttribute("password","123456");
        return "answer";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        System.out.println(modelMap.getClass().getName());
        // org.springframework.validation.support.BindingAwareModelMap
        modelMap.addAttribute("username","shengyi");
        modelMap.addAttribute("password","123456");
        return "answer";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        System.out.println(map.getClass().getName());
        // org.springframework.validation.support.BindingAwareModelMap
        map.put("username","shengyi");
        map.put("password","123456");
        return "answer";
    }
}
