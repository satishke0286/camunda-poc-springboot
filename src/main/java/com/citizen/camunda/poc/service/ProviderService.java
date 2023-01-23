package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.ProviderModel;
import com.citizen.camunda.poc.model.ProviderReviewModel;

import java.util.List;
import java.util.Map;

public interface ProviderService {
    List<ProviderModel> getAllProvider();

    Map<String, String> reviewProvider(ProviderReviewModel providerReviewModel);
}
