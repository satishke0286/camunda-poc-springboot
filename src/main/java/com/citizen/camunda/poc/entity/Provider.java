package com.citizen.camunda.poc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PROVIDER")
public class Provider {
    private static final long serialVersionUID = -6313603058414149656L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NPI")
    private String npi;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "STATUS")
    private String status;

}
