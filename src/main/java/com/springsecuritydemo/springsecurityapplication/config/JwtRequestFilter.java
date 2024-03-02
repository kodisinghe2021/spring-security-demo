package com.springsecuritydemo.springsecurityapplication.config;

import com.springsecuritydemo.springsecurityapplication.service.impl.JwtService;
import com.springsecuritydemo.springsecurityapplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// this class for inserting customizable filters to the filter chain
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private JwtService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // the whole request can catch in here
    // catch the header
    final String requestTokenHeader = request.getHeader("Authorization");

    String userName = "";
    String jwtToken = "";

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        userName = jwtUtil.getUsernameFromToken(jwtToken);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = jwtService.loadUserByUsername(userName);
          if (userDetails != null && jwtUtil.isValidateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
        }
      } catch (RuntimeException e) {
        logger.error(e);
        // throw new RuntimeException(e);
      }
    } else {
      logger.info("JWT NOT FOUND");
    }
    filterChain.doFilter(request, response);
  }
}
