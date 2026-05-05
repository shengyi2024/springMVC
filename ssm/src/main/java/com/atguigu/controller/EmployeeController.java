package com.atguigu.controller;

import com.atguigu.pojo.Employee;
import com.atguigu.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 使用REST风格实现员工管理
 * 1.查询所有用户信息 /employee get
 * 2.根据id查询员工信息 /employee/1 get
 * 3.跳转到添加页面 /to/add get
 * 4.添加员工信息 /employee post
 * 5.跳转到修改页面 /to/update get
 * 6.修改员工信息 /employee put
 * 7.删除员工信息 /employee/1 delete
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 使用分页功能查询所有员工信息
     * /employee/page/#{pageNum} GET
     * @param model
     * @return
     */
    @RequestMapping(value = "/employee/page/{pageNum}",method = RequestMethod.GET)
    public String getAllEmployeeByPageHelper(@PathVariable("pageNum") Integer pageNum, Model model){
        // 获取分页相关数据
        PageInfo<Employee> pageInfo = employeeService.getAllEmployeeByPageHelper(pageNum);
        // 将分页相关数据共享到请求域中
        model.addAttribute("page",pageInfo);

        return "employee_list";
    }
//    @RequestMapping(value = "/employee",method = RequestMethod.GET)
//    public String getAllEmployee(Model model){
//        // 查询所有员工信息
//        List<Employee> list = employeeService.getAllEmployee();
//        // 将员工信息在请求域中共享
//        model.addAttribute("list",list);
//        // 跳转到employee_list.html
//        return "employee_list";
//    }
}
