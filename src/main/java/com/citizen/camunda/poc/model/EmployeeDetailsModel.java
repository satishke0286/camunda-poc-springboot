package com.citizen.camunda.poc.model;

import lombok.Data;

import javax.persistence.*;

@Data
public class EmployeeDetailsModel {
    private long id;

    private String employee;

    private String birthDate;

    private String hireDate;

    private String position;

    private String address;

    private String city;

    private String state;

    private String homePhone;

    private String mobilePhone;

    private String skype;

    private String email;

}
