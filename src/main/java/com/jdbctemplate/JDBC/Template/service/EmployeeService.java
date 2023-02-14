package com.jdbctemplate.JDBC.Template.service;

import com.jdbctemplate.JDBC.Template.common.SunriseWincashException;
import com.jdbctemplate.JDBC.Template.entity.Employee;

import java.util.List;

public interface EmployeeService {

     List<Employee> getAllEmployee();


     int save(Employee employee);

     int update(Employee employee) throws SunriseWincashException;

     Employee deleteById(Long id) throws SunriseWincashException;

     Employee getById(Long id) throws SunriseWincashException;
}
