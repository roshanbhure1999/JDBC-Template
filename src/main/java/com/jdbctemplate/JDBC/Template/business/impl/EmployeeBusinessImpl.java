package com.jdbctemplate.JDBC.Template.business.impl;

import com.jdbctemplate.JDBC.Template.business.EmployeeBusiness;
import com.jdbctemplate.JDBC.Template.common.ExceptionUtil;
import com.jdbctemplate.JDBC.Template.common.SunriseWincashException;
import com.jdbctemplate.JDBC.Template.constant.ApplicationConstant;
import com.jdbctemplate.JDBC.Template.constant.ExceptionConstant;
import com.jdbctemplate.JDBC.Template.entity.Employee;
import com.jdbctemplate.JDBC.Template.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Slf4j
@Service
public class EmployeeBusinessImpl implements EmployeeBusiness {

    @Autowired
    private EmployeeService employeeService;



    @Override
    public List<Employee> getAllEmployee() throws SunriseWincashException {
        List<Employee> allEmployee = null;

        try {

            allEmployee = employeeService.getAllEmployee();
            log.info("The Employee List size :: {}", allEmployee.size());

        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception,
                    "EmployeeBusinessImpl.java :: getAllEmployee()", ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);

        }
        return allEmployee;
    }

    @Override
    public int saveEmployee(Employee employee) throws SunriseWincashException {

        try {
            if (StringUtils.isEmpty(employee.getFirstName())) {
                log.error("please enter valid name");
                return 0;
            }
            return employeeService.save(employee);
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception,
                    "EmployeeBusinessImpl.java :: saveEmployee()",ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);
        }
    }

    @Override
    public Employee deleteById(Long id) throws SunriseWincashException {
        try {
            return employeeService.deleteById(id);
        } catch (SunriseWincashException sunriseWincashException) {
            throw sunriseWincashException;
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception, "EmployeeBusinessImpl.java :: deleteById()",
                    ExceptionConstant.ERROR_LEVEL_IS_MAJOR);
        }

    }

    @Override
    public int update(Employee employee) throws SunriseWincashException {
        try {
            return employeeService.update(employee);
        } catch (SunriseWincashException sunriseWincashException) {
            throw sunriseWincashException;
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "9999_NA",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception, "EmployeeBusinessImpl.java :: update()",
                    ExceptionConstant.ERROR_LEVEL_IS_MINOR);
        }
    }

    @Override
    public Employee getById(Long id) throws SunriseWincashException {
        try {
            return employeeService.getById(id);
        } catch (SunriseWincashException sunriseWincashException) {
            throw sunriseWincashException;
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception, this.getClass().getSimpleName() +"::->"+new Object(){}.getClass().getEnclosingMethod().getName(),ExceptionConstant.ERROR_LEVEL_IS_NORMAL);
        }
    }
}
