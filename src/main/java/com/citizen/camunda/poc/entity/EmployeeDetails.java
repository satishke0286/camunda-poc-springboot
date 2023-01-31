package com.citizen.camunda.poc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails {
    private static final long serialVersionUID = -6313603058414149656L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "EMPLOYEE")
    private String employee;

    @Column(name = "BIRTH_DATE")
    private String birthDate;

    @Column(name = "HIRE_DATE")
    private String hireDate;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "HOME_PHONE")
    private String homePhone;

    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;

    @Column(name = "SKYPE")
    private String skype;

    @Column(name = "EMAIL")
    private String email;


}
