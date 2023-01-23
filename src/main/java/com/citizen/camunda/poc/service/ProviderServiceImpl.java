package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.entity.Provider;
import com.citizen.camunda.poc.model.ProviderModel;
import com.citizen.camunda.poc.model.ProviderReviewModel;
import com.citizen.camunda.poc.repo.ProviderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService{

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<ProviderModel> getAllProvider() {
        List<Provider> allProviders = providerRepository.findAll();
        return allProviders.stream().map(this::getProviderModel).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> reviewProvider(ProviderReviewModel providerReviewModel) {
        providerRepository.updateStatusById(providerReviewModel.getId(), providerReviewModel.getStatus());
        return Collections.singletonMap("status", providerReviewModel.getStatus());
    }

    private ProviderModel getProviderModel(Provider providerEntity) {
        ProviderModel providerModel = new ProviderModel();
        BeanUtils.copyProperties(providerEntity, providerModel);
        return providerModel;
    }
}
