package com.springsecuritydemo.springsecurityapplication.repository;

import com.springsecuritydemo.springsecurityapplication.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel,String> {
}
