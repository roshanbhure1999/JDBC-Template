package com.jdbctemplate.JDBC.Template.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String pinCode;
    private String address;
    private String contact;
    private double salary ;
    private String companyName;
    private String companyLocation;

}
