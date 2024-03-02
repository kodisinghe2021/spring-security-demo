package com.springsecuritydemo.springsecurityapplication.dto;


public class RegistrationRequest {
  private String userName;
  private String email;
  private String name;
  private String password;
  private String roleModelId;

  public RegistrationRequest(String userName, String email, String name, String password, String roleModelId) {
    this.userName = userName;
    this.email = email;
    this.name = name;
    this.password = password;
    this.roleModelId = roleModelId;
  }

  public RegistrationRequest() {
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoleModelId() {
    return roleModelId;
  }

  public void setRoleModelId(String roleModelId) {
    this.roleModelId = roleModelId;
  }
}
