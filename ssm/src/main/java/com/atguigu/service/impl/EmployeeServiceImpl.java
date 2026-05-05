package com.atguigu.service.impl;

import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.pojo.Employee;
import com.atguigu.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    // <bean class="org.mybatis.spring.SqlSessionFactoryBean"></bean>
    // @Autowired
    // private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

    @Override
    public PageInfo<Employee> getAllEmployeeByPageHelper(Integer pageNum) {
        // 开启分页功能 pageSize:每页显示的条数
        PageHelper.startPage(pageNum, 5);
        // 查询所有员工信息
        List<Employee> list = this.getAllEmployee();
        return new PageInfo<Employee>(list,5);
    }
}


