package com.springsecuritydemo.springsecurityapplication.dto;

import com.springsecuritydemo.springsecurityapplication.model.UserModel;

public class LoginResponse {
  private UserModel userModel;

  private String jwtToken;


  public LoginResponse() {
  }

  public LoginResponse(UserModel userModel, String jwtToken) {
    this.userModel = userModel;
    this.jwtToken = jwtToken;
  }

  public UserModel getUserModel() {
    return userModel;
  }

  public void setUserModel(UserModel userModel) {
    this.userModel = userModel;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }
}
