package com.shengyi.restful.controller;

import com.shengyi.restful.dao.EmployeeDao;
import com.shengyi.restful.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class EmployeeController {

    /**
     * 查询所有员工信息 /employee  GET
     * 跳转到添加员工页面 /to/add  GET
     * 新增员工信息 /employee  POST
     * 跳转到修改员工页面 /to/update/1  GET
     * 修改员工信息 /employee  PUT
     * 删除员工信息 /employee/1  DELETE
     */
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String getAllEmployee(Model model){
        // 获取所有员工信息
        Collection<Employee> employees = employeeDao.getAll();
        // 将所有员工信息放到model中
        model.addAttribute("employees",employees);
        // 跳转到列表页面
        return "emp_list";
    }

    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public String addEmployee(Employee employee){
        // 添加员工
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public String updateEmployee(@PathVariable("id") Integer id,Model model){
        // 根据id查询员工
        Employee employee = employeeDao.get(id);
        // 将员工信息放在model中
        model.addAttribute("employee",employee);
        return "emp_update";
    }

    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        System.out.println("修改员工信息");
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        System.out.println("删除员工信息");
        return "redirect:/employee";
    }
}
