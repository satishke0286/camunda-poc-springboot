package com.citizen.camunda.poc.config;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {
  private final String authority;

  public GrantedAuthorityImpl(String authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return authority;
  }
}
