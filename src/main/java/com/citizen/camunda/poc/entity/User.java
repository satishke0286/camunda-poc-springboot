package com.citizen.camunda.poc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_DETAILS")
public class User {
//    private static final long serialVersionUID = -6313603058414149656L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

}
