package com.springsecuritydemo.springsecurityapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleModel {
  @Id
  @Column(name = "role_id")
  private String roleId;

  @Column(name = "role_description")
  private String roleDiscription;
}
