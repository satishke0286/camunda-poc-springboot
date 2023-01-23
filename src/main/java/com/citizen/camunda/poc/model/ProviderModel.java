package com.citizen.camunda.poc.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
public class ProviderModel {
    private Long id;
    private String name;
    private String npi;
    private String address;
    private String type;
    private String status;
}
