package com.atguigu.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试注解配置异常解析器
 * @ControllerAdvice 将当前类标识为异常处理的组件
 *     针对所有的@Controller或者@RestController控制器类进行异常处理
 * @ExceptionHandler value属性值标识方法处理的异常
 *      任何出现指定异常的控制器方法，都会执行此方法
 */
@ControllerAdvice
public class TestAnnotationExceptionResolverController {

    @ExceptionHandler(ArithmeticException.class)
    public String handleException(Throwable ex,Model model){
        // ex表示控制器方法所出现的异常
        model.addAttribute("ex", "异常为"+ex);
        return "error";
    }
}
