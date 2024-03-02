package com.springsecuritydemo.springsecurityapplication.service.impl;

import com.springsecuritydemo.springsecurityapplication.model.RoleModel;
import com.springsecuritydemo.springsecurityapplication.repository.RoleRepo;
import com.springsecuritydemo.springsecurityapplication.service.RoleService;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeOperationsException;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  RoleRepo roleRepo;
  @Override
  public String createRole(RoleModel roleModel) {
    try {
      roleModel.setRoleId("ROLE_"+roleModel.getRoleId().toUpperCase());
      String id = roleRepo.save(roleModel).getRoleId();
      if(!id.isEmpty()){
        return id;
      }else {
        throw new RuntimeException();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
