package com.citizen.camunda.poc.controller;

import com.citizen.camunda.poc.model.EmployeeDetailsModel;
import com.citizen.camunda.poc.model.UserModel;
import com.citizen.camunda.poc.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/")
public class AuthController {
  private final IUserService userAccessService;

  private final HttpSession httpSession;

  @Inject
  public AuthController(IUserService userAccessService, HttpSession httpSession) {
    this.userAccessService = userAccessService;
    this.httpSession = httpSession;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
    Optional<String> u = userAccessService.login(httpSession.getId(), username, password);
    if (u.isPresent()) {
      return ResponseEntity.ok().body(u.get());
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody UserModel userModel) {
      userAccessService.saveUser(userModel);
      return ResponseEntity.ok().body("Successfully saved user details.");
   }

  @PostMapping("/logout")
  public ResponseEntity<String> logout() {
    userAccessService.logout(httpSession.getId());
    return ResponseEntity.ok().body("logout success!");
  }

  @GetMapping("/getAllEmployee")
  public ResponseEntity<List<EmployeeDetailsModel>> getAllEmployee() {
    return ResponseEntity.ok().body(userAccessService.getAllEmployee());
  }

}
