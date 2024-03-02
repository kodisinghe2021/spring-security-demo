package com.springsecuritydemo.springsecurityapplication.repository;

import com.springsecuritydemo.springsecurityapplication.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepo extends JpaRepository<UserModel,Integer> {
  List<UserModel> findByEmailEquals(String un);
  List<UserModel> findByUserNameEquals(String un);
}
