package com.citizen.camunda.poc.controller;

import com.citizen.camunda.poc.model.Data;
import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.service.IDataService;
import com.citizen.camunda.poc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/data")
public class DataController {

  private static final Logger logger = LoggerFactory.getLogger(DataController.class);

  private final IDataService dataService;
  private final IUserService userService;

  @Inject
  public DataController(IDataService dataService, IUserService userService) {
    this.dataService = dataService;
    this.userService = userService;
  }

//  @Secured({"ROLE_USER", "ROLE_ADMIN"})
//  @GetMapping("/userDetails")
//  public ResponseEntity<User> getForUsersData(Authentication authentication) {
//    logger.info("getData: authentication={}", authentication.getName());
//    authentication.getAuthorities().forEach(a -> {
//      logger.info("  authority={}", a.getAuthority());
//    });
//    return ResponseEntity.ok().body(
//      dataService.getSecuredData("Secured for USER/ADMIN " + authentication.getName()));
//  }

  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  @GetMapping("/users/all")
  public ResponseEntity<Data> getForUsersData(Authentication authentication) {
    logger.info("getData: authentication={}", authentication.getName());
    authentication.getAuthorities().forEach(a -> {
      logger.info("  authority={}", a.getAuthority());
    });
    return ResponseEntity.ok().body(
      dataService.getSecuredData("Secured for USER/ADMIN " + authentication.getName()));
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/admins/all")
  public ResponseEntity<Data> getDataForAdmins(Authentication authentication) {
    logger.info("getData: authentication={}", authentication.getName());
    authentication.getAuthorities().forEach(a -> {
      logger.info("  authority={}", a.getAuthority());
    });
    return ResponseEntity.ok()
      .body(dataService.getSecuredData("Secured for ADMIN " + authentication.getName()));
  }

  @Secured("ROLE_ADMIN")
  @PutMapping("/admins/state/{state}")
  public ResponseEntity<Data> setStateForAdmins(Authentication authentication,
                                                @PathVariable String state) {
    logger.info("setState: authentication={} state={}", authentication.getName(), state);
    authentication.getAuthorities().forEach(a -> {
      logger.info("  authority={}", a.getAuthority());
    });
    dataService.setState(state);
    return ResponseEntity.ok().build();
  }

  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  @GetMapping("/usersDetails")
  public ResponseEntity<User> getForUsersData(HttpServletRequest httpRequest) {
    logger.info("getData: authentication={}", httpRequest.getHeader("token"));
    return ResponseEntity.ok().body(
            userService.isAuthenticated(httpRequest.getHeader("token")).orElse(null));
  }

}
