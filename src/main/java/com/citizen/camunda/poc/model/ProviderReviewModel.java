package com.citizen.camunda.poc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProviderReviewModel {
    private Long id;
    private String type;
    private String status;
}
