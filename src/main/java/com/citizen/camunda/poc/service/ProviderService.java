package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.LimitedProviderModel;
import com.citizen.camunda.poc.model.ProviderModel;
import com.citizen.camunda.poc.model.ProviderReviewModel;

import java.util.List;
import java.util.Map;

public interface ProviderService {
    List<ProviderModel> getAllProvider();

    List<LimitedProviderModel> getAllLimitedProvider();

    Map<String, String> reviewProvider(ProviderReviewModel providerReviewModel);
}
