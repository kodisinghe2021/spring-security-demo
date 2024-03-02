package com.springsecuritydemo.springsecurityapplication.controller;

import com.springsecuritydemo.springsecurityapplication.model.RoleModel;
import com.springsecuritydemo.springsecurityapplication.service.RoleService;
import com.springsecuritydemo.springsecurityapplication.util.ResMessage;
import com.springsecuritydemo.springsecurityapplication.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

  @Autowired
  RoleService roleService;

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<StandardResponse> createRole(@RequestBody RoleModel roleModel){
   String id = roleService.createRole(roleModel);
    return new ResponseEntity<>(new StandardResponse(200, ResMessage.SUCCESS.name(), id), HttpStatus.CREATED);
  }
}
