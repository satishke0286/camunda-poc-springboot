package com.citizen.camunda.poc.config;

import com.citizen.camunda.poc.service.IUserService;
import com.citizen.camunda.poc.service.ProviderService;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.inject.Inject;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
  private final Enforcer enforcer;
  private final IUserService userAccessService;

  @Inject
  public SecurityConfiguration(Enforcer enforcer, IUserService userAccessService) {
    this.enforcer = enforcer;
    this.userAccessService = userAccessService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .addFilterBefore(new CasbinFilter(enforcer, userAccessService), BasicAuthenticationFilter.class)
      .antMatcher("/data/**").antMatcher("/provider/**")
      .csrf().disable();
    return http.build();
  }
}
