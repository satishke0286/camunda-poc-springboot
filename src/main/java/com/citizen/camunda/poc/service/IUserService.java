package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.EmployeeDetailsModel;
import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {
  Optional<String> login(String sessionId, String username, String password);

  Optional<User> isAuthenticated(String sessionId);

  void logout(String sessionId);

  void saveUser(UserModel userModel);

  List<EmployeeDetailsModel> getAllEmployee();
}
