package com.springsecuritydemo.springsecurityapplication.controller;

import com.springsecuritydemo.springsecurityapplication.dto.RegistrationRequest;
import com.springsecuritydemo.springsecurityapplication.model.UserModel;
import com.springsecuritydemo.springsecurityapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping("register-new-user")
  public UserModel registerNewUser(@RequestBody RegistrationRequest registrationRequest){
    return userService.registerNewUser(registrationRequest);
  }

  // this will run initially
  @PostConstruct
  public void initRoleAndUser(){
    userService.initRoleAndUser();
  }

  @GetMapping("for-admin")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String forAdmin(){
    return "This url is only accessible to admin";
  }

  @GetMapping("for-user")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public String forUser(){
    return "This url is only accessible to user";
  }

}
