package com.springsecuritydemo.springsecurityapplication.service;


import com.springsecuritydemo.springsecurityapplication.model.RoleModel;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
  String createRole(RoleModel roleModel);
}
