package com.springsecuritydemo.springsecurityapplication.repository;

import com.springsecuritydemo.springsecurityapplication.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel,String> {
}
