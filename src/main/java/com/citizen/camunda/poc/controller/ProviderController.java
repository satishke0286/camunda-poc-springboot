package com.citizen.camunda.poc.controller;

import com.citizen.camunda.poc.model.ProviderModel;
import com.citizen.camunda.poc.model.ProviderReviewModel;
import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.service.IUserService;
import com.citizen.camunda.poc.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private IUserService userAccessService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<ProviderModel> getAllProvider() {
        return providerService.getAllProvider();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/review")
    public Map<String, String> getAllProvider(@RequestBody ProviderReviewModel providerReviewModel) {
        return providerService.reviewProvider(providerReviewModel);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/usersDetails")
    public ResponseEntity<User> getForUsersData(HttpServletRequest httpRequest) {
        return ResponseEntity.ok().body(
                userAccessService.isAuthenticated(httpRequest.getHeader("token")).orElse(null));
    }
}
