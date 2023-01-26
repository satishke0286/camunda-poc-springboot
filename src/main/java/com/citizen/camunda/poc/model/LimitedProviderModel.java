package com.citizen.camunda.poc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LimitedProviderModel {
    private Long id;
    private String name;
    private String address;
    private String type;
    private String status;
}
