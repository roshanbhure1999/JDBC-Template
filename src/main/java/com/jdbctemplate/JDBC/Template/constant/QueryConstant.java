package com.jdbctemplate.JDBC.Template.constant;

public class QueryConstant {

    public static final String GET_ALL_EMPLOYEE = "select * from employee";

    public static final String SAVE_EMPLOYEE_DETAILS = "insert into employee (name,department,email,address,contact,salary) values(?,?,?,?,?,?)";
    public static final String DELETE_BY_ID = "delete  from employee where id =?";

    public static final String UPDATE = "UPDATE employee  SET  name=?,department=?,email=?,address=?,contact=?,salary=? WHERE id=?";

    public static final String GET_BY_ID = "select * from employee WHERE id=?";


    // name department email address contact salary

}
