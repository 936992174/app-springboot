package com.peas.springboot.controller;

import com.peas.springboot.dao.DepartmentDao;
import com.peas.springboot.dao.EmployeeDao;
import com.peas.springboot.entities.Department;
import com.peas.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/ems")
    public String list(Model model, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAdd(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //redirect
        employeeDao.save(employee);
        return "redirect:/ems";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmploy(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/ems";
    }

}
