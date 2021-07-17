package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> findAllBySalaryGreaterThatBoss() {
        return employeeDao.findAllWhereSalaryGreaterThatBoss();
    }

    @Override
    public List<Employee> findAllByMaxSalary() {
        return employeeDao.findAllByMaxSalary();
    }

    @Override
    public List<Employee> findAllWithoutBoss() {
        return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        Employee employee = ((Collection<Employee>) employees).stream()
                .filter(e -> e.getName().equals(name))
                .findFirst().get();
        ((Collection<Employee>) employees).remove(employee);

        employeeDao.delete(employee);
        employeeDao.saveAll(employees);
        return employee.getId();
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        Employee employee = ((Collection<Employee>) employees).stream()
                .filter(e -> e.getName().equals(name))
                .findFirst().get();

        employee.setSalary(BigDecimal.TEN);
        employeeDao.saveAll(employees);
        return employee.getId();
    }

    @Override
    public Long hireEmployee(Employee employee) {
        return employeeDao.save(employee).getId();
    }
}