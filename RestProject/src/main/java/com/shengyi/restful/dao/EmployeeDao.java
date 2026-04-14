package com.shengyi.restful.dao;

import com.shengyi.restful.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    static{
        employees = new HashMap<Integer,Employee>();

        employees.put(1, new Employee(1,"E-AA","aa@163.com",1));
        employees.put(2, new Employee(2,"E-BB","bb@163.com",1));
        employees.put(3, new Employee(3,"E-CC","cc@163.com",0));
        employees.put(4, new Employee(4,"E-DD","dd@163.com",0));
        employees.put(5, new Employee(5,"E-EE","ee@163.com",1));
    }

    private static Integer initId = 6;

    /**
     * 实现添加和修改员工信息
     * @param employee
     */
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employees.put(employee.getId(), employee);
    }

    /**
     * 查询所有员工信息
     * @return
     */
    public Collection<Employee> getAll(){
        return employees.values();
    }

    /**
     * 通过id查询员工
     * @param id
     * @return
     */
    public Employee get(Integer id){
        return employees.get(id);
    }

    /**
     * 通过id删除员工
     * @param id
     */
    public void delete(Integer id){
        employees.remove(id);
    }
}
