package com.citizen.camunda.poc.config;

import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.service.IUserService;
import org.casbin.jcasbin.main.Enforcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CasbinFilter implements Filter {
  private static final Logger logger = LoggerFactory.getLogger(CasbinFilter.class);

  private final Enforcer enforcer;
  private final IUserService userAccessService;

  public CasbinFilter(Enforcer enforcer, IUserService userAccessService) {
    this.enforcer = enforcer;
    this.userAccessService = userAccessService;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    logger.info("initializing ...");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    System.out.println("token >>>> " + httpServletRequest.getParameter("token"));
    String sessionId = httpServletRequest.getParameter("token");
    Optional<User> u = userAccessService.isAuthenticated(sessionId);
    if (u.isPresent()) {
      String user = u.get().getUsername();
      String method = httpServletRequest.getMethod();
      String path = httpServletRequest.getRequestURI();

      if (enforcer.enforce(user, path, method)) {
        logger.info("session is authorized: {} {} {} {}", sessionId, user, method, path);
        List<String> rolesForUser = enforcer.getRolesForUser(user);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(new AuthenticationImpl(u.get().getUsername(), rolesForUser));
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        chain.doFilter(request, response);
      } else {
        logger.error("session is not authorized: {} {} {} {}", sessionId, user, method, path);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
      }
    } else {
      logger.error("session is not authenticated: {}", sessionId);
      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
      httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
    }
  }

  @Override
  public void destroy() {
    logger.info("destroy.");
  }
}
