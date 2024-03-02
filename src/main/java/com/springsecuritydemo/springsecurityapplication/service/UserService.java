package com.springsecuritydemo.springsecurityapplication.service;

import com.springsecuritydemo.springsecurityapplication.dto.RegistrationRequest;
import com.springsecuritydemo.springsecurityapplication.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  UserModel registerNewUser(RegistrationRequest registrationRequest);

  void initRoleAndUser();
}
