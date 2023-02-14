package com.jdbctemplate.JDBC.Template.business;

import com.jdbctemplate.JDBC.Template.common.SunriseWincashException;
import com.jdbctemplate.JDBC.Template.entity.Employee;

import java.util.List;

public interface EmployeeBusiness {
    List<Employee> getAllEmployee() throws SunriseWincashException;

    int saveEmployee(Employee employee) throws SunriseWincashException;

    Employee deleteById(Long id)throws SunriseWincashException;

    int update(Employee employee) throws SunriseWincashException;

    Employee getById(Long id) throws SunriseWincashException;
}
