package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.Data;
import com.citizen.camunda.poc.model.User;

public interface IDataService {
  void setState(String state);

  Data getSecuredData(String source);

}
