package com.atguigu.service;

import com.atguigu.pojo.Employee;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    PageInfo<Employee> getAllEmployeeByPageHelper(Integer pageNum);
}
