package com.citizen.camunda.poc.model;

public class User {
  private final String username;
  private final String password;
  private final String firstName;
  private final String lastName;

  public User(String username, String password, String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }

  public boolean verifyPassword(String password) {
    return this.password.equals(password);
  }
}
