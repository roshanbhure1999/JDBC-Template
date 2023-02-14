package com.jdbctemplate.JDBC.Template.service.impl;

import com.jdbctemplate.JDBC.Template.common.ExceptionUtil;
import com.jdbctemplate.JDBC.Template.common.SunriseWincashException;
import com.jdbctemplate.JDBC.Template.constant.ApplicationConstant;
import com.jdbctemplate.JDBC.Template.constant.ExceptionConstant;
import com.jdbctemplate.JDBC.Template.constant.QueryConstant;
import com.jdbctemplate.JDBC.Template.entity.Employee;
import com.jdbctemplate.JDBC.Template.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployee() {
        return jdbcTemplate.query(QueryConstant.GET_ALL_EMPLOYEE,
                (rs, rowNum) -> Employee.builder().
                        id(rs.getLong(ApplicationConstant.ID))
                        .firstName(rs.getString(ApplicationConstant.FIRST_NAME))
                        .lastName(rs.getString(ApplicationConstant.LAST_NAME))
                        .address(rs.getString(ApplicationConstant.ADDRESS))
                        .companyLocation(rs.getString(ApplicationConstant.COMPANY_LOCATION))
                        .salary(rs.getLong(ApplicationConstant.SALARY))
                        .pinCode(rs.getString(ApplicationConstant.PIN_CODE))
                        .companyName(rs.getString(ApplicationConstant.COMPANY_NAME))
                        .contact(rs.getString(ApplicationConstant.CONTACT))

                        .build());

    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(QueryConstant.SAVE_EMPLOYEE_DETAILS,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAddress(),
                employee.getCompanyLocation(),
                employee.getPinCode(),
                employee.getCompanyName(),
                employee.getContact(),
                employee.getSalary());

    }


    @Override
    public int update(Employee employee) throws SunriseWincashException {
        try {
            return jdbcTemplate.update(QueryConstant.UPDATE,
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAddress(),
                    employee.getPinCode(),
                    employee.getCompanyName(),
                    employee.getCompanyLocation(),
                    employee.getContact(),
                    employee.getSalary());
        } catch (DataAccessException dataAccessException) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.BAD_REQUEST, "2291_SW", "Please enter valid id to Update Employee",
                    ApplicationConstant.SOURCE, dataAccessException,
                    this.getClass().getSimpleName()+" :: "+new Object(){}.getClass().getEnclosingMethod().getName(), ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE, exception,
                    "EmployeeServiceImpl.java :: update()", ExceptionConstant.ERROR_LEVEL_IS_CRITICAL);

        }
    }

    /**
     * @param id
     * @return Employee
     * @throws SunriseWincashException
     */
    @Override
    public Employee deleteById(Long id) throws SunriseWincashException {
        try {
            Employee employee = jdbcTemplate.queryForObject(QueryConstant.DELETE_BY_ID, new Object[]{id}, new RowMapper<Employee>() {
                @Override
                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return Employee.builder().id(rs.getLong(ApplicationConstant.ID))
                            .firstName(rs.getString(ApplicationConstant.FIRST_NAME))
                            .lastName(rs.getString(ApplicationConstant.LAST_NAME))
                            .address(rs.getString(ApplicationConstant.ADDRESS))
                            .companyLocation(rs.getString(ApplicationConstant.COMPANY_LOCATION))
                            .salary(rs.getLong(ApplicationConstant.SALARY))
                            .pinCode(rs.getString(ApplicationConstant.PIN_CODE))
                            .companyName(rs.getString(ApplicationConstant.COMPANY_NAME))
                            .contact(rs.getString(ApplicationConstant.CONTACT))
                            .build();
                }
            });
            return employee;

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.BAD_REQUEST, "2291_SW", "Please enter valid id for deletion",
                    ApplicationConstant.SOURCE, dataIntegrityViolationException,
                    "EmployeeServiceImpl.java :: deleteById()", ExceptionConstant.ERROR_LEVEL_IS_MINOR);
        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE,
                    exception, "EmployeeServiceImpl.java :: deleteById()", ExceptionConstant.ERROR_LEVEL_IS_MINOR);
        }
    }

    /**
     * @param id
     * @return Employee
     * @throws SunriseWincashException
     */
    @Override
    public Employee getById(Long id) throws SunriseWincashException {
        try {
            Employee employee = jdbcTemplate.queryForObject(QueryConstant.GET_BY_ID, new Object[]{id}, new RowMapper<Employee>() {
                @Override
                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return Employee.builder().id(rs.getLong(ApplicationConstant.ID))
                            .firstName(rs.getString(ApplicationConstant.FIRST_NAME))
                            .lastName(rs.getString(ApplicationConstant.LAST_NAME))
                            .address(rs.getString(ApplicationConstant.ADDRESS))
                            .companyLocation(rs.getString(ApplicationConstant.COMPANY_LOCATION))
                            .salary(rs.getLong(ApplicationConstant.SALARY))
                            .pinCode(rs.getString(ApplicationConstant.PIN_CODE))
                            .companyName(rs.getString(ApplicationConstant.COMPANY_NAME))
                            .contact(rs.getString(ApplicationConstant.CONTACT))
                            .build();
                }
            });
            return employee;

        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.BAD_REQUEST, "2221_SW",
                    "No such employee is found", ApplicationConstant.SOURCE,
                    emptyResultDataAccessException,this.getClass().getSimpleName() +"::->"+new Object(){}.getClass().getEnclosingMethod().getName(), ExceptionConstant.ERROR_LEVEL_IS_MINOR);

        } catch (Exception exception) {
            throw ExceptionUtil.createSunriseWincashException(HttpStatus.INTERNAL_SERVER_ERROR, "500_SW",
                    "Internal Server Error", ApplicationConstant.SOURCE,
                    exception, "EmployeeServiceImpl.java :: getById()", ExceptionConstant.ERROR_LEVEL_IS_MAJOR);
        }

    }

}
