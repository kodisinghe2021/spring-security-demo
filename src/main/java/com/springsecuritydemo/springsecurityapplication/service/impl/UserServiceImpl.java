package com.springsecuritydemo.springsecurityapplication.service.impl;

import com.springsecuritydemo.springsecurityapplication.dto.RegistrationRequest;
import com.springsecuritydemo.springsecurityapplication.model.RoleModel;
import com.springsecuritydemo.springsecurityapplication.model.UserModel;
import com.springsecuritydemo.springsecurityapplication.repository.RoleRepo;
import com.springsecuritydemo.springsecurityapplication.repository.UserRepo;
import com.springsecuritydemo.springsecurityapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepo userRepo;
  @Autowired
  private RoleRepo roleRepo;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserModel registerNewUser(RegistrationRequest registrationRequest) {
    List<RoleModel> lst = new ArrayList<>();
    lst.add(roleRepo.findById(registrationRequest.getRoleModelId()).get());
    if(!userRepo.existsById(registrationRequest.getUserName())){
      UserModel m = userRepo.save(
              new UserModel(
                      registrationRequest.getUserName(),
                      registrationRequest.getEmail(),
                      registrationRequest.getName(),
                      passwordEncoder.encode(registrationRequest.getPassword()),
                      lst
              )
      );
    return m;
    }else {
      throw new RuntimeException("USER_ALREADY_REGISTERED");
    }

  }

  public void initRoleAndUser() {

    // if role table haven't id (String) like below then ...
    if (!roleRepo.existsById("ROLE_ADMIN")) {
      RoleModel adminModel = roleRepo.save(new RoleModel(
              "ROLE_ADMIN",
              "can make login credentials"
      ));
      userRepo.save(new UserModel(
              "admin123",
              "admin123@gmail.com",
              "admin_1",
              passwordEncoder.encode("1234"),
              new ArrayList<>(Collections.singletonList(adminModel))
      ));
    }

    if (!roleRepo.existsById("ROLE_USER")) {
      RoleModel userModel = roleRepo.save(new RoleModel(
              "ROLE_USER",
              "can handle any requests"
      ));
      userRepo.save(new UserModel(
              "user123",
              "user123@gmail.com",
              "user_1",
              passwordEncoder.encode("1234"),
              new ArrayList<>(Collections.singletonList(userModel))
      ));
    }

  }
}
