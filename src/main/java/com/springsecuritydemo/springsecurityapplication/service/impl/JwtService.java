package com.springsecuritydemo.springsecurityapplication.service.impl;

import com.springsecuritydemo.springsecurityapplication.controller.LoginController;
import com.springsecuritydemo.springsecurityapplication.dto.LoginRequest;
import com.springsecuritydemo.springsecurityapplication.dto.LoginResponse;
import com.springsecuritydemo.springsecurityapplication.model.RoleModel;
import com.springsecuritydemo.springsecurityapplication.model.UserModel;
import com.springsecuritydemo.springsecurityapplication.repository.UserRepo;
import com.springsecuritydemo.springsecurityapplication.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
  @Autowired
  private UserRepo userRepo;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;
  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserModel> user = userRepo.findById(username);

    if (user.isPresent()) {
      return new org.springframework.security.core.userdetails.User(
              user.get().getUserName(),
              user.get().getPassword(),
              getAuthority(user.get())
      );
    } else {
      throw new UsernameNotFoundException("user-not-found-with-username" + username);
    }
  }
  private Set getAuthority(UserModel user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    for (RoleModel role : user.getRoleModelsList()) {
      authorities.add(new SimpleGrantedAuthority(role.getRoleId()));
    }
    return authorities;
  }
  public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception {
    String username = loginRequest.getUsername();
    String userPassword = loginRequest.getUserPassword();

    authenticateCst(username, userPassword);

    UserDetails userDetails = loadUserByUsername(username);

    String newGeneratedToken = jwtUtil.generateToken(userDetails);

    UserModel userModel = userRepo.findById(username).get();
    return new LoginResponse(
            userModel,
            newGeneratedToken
    );
  }

  private void authenticateCst(String username, String userPassword) {
    logger.info("authenticateCst details: "+username+" "+userPassword);
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPassword));

    } catch (AuthenticationException e) {
    logger.error("authenticateCst Failed: "+e);
      throw new RuntimeException(e);
    }
  }
}
