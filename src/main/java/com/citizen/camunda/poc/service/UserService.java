package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.model.UserModel;
import com.citizen.camunda.poc.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService implements IUserService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final Map<String, User> sessions;
  private final Map<String, User> users;

  @Autowired
  private UserRepository userRepository;

  public UserService() {
    this.sessions = new ConcurrentHashMap<>();
    this.users = new HashMap<>();
    this.users.put("admin", new User("admin", "pwd", "John", "Bob"));
    this.users.put("user", new User("user", "pwd", "Steve", "Smith"));
    this.users.put("subuser", new User("subuser", "pwd", "Mathew", "Shan"));
  }

  @Override
  public Optional<String> login(String sessionId, String username, String password) {
    User u = users.get(username);
    if (u != null && u.verifyPassword(password)) {
      logger.info("login OK: {} {}", sessionId, username);
      sessions.put(sessionId, u);
      return Optional.of(sessionId);
    }
    logger.info("login Failed: {} {}", sessionId, username);
    return Optional.empty();
  }

  @Override
  public Optional<User> isAuthenticated(String sessionId) {
    return Optional.ofNullable(sessions.get(sessionId));
  }

  @Override
  public void logout(String sessionId) {
    logger.info("logout: {}", sessionId);
    sessions.remove(sessionId);
  }

  @Override
  public void saveUser(UserModel userModel) {
    com.citizen.camunda.poc.entity.User user = new com.citizen.camunda.poc.entity.User();
    BeanUtils.copyProperties(userModel, user);
//    user.setId(UUID.randomUUID().toString());
    userRepository.save(user);
  }

}
