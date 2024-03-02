package com.springsecuritydemo.springsecurityapplication.controller;

import com.springsecuritydemo.springsecurityapplication.dto.LoginRequest;
import com.springsecuritydemo.springsecurityapplication.dto.LoginResponse;
import com.springsecuritydemo.springsecurityapplication.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoginController {
  @Autowired
  private JwtService jwtService;
  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @PostMapping("/authentication")
  public LoginResponse createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) {
    logger.info("Request body: " + loginRequest.getUsername() + " " + loginRequest.getUserPassword());
    try {
      LoginResponse res = jwtService.createJwtToken(loginRequest);
      return res;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
