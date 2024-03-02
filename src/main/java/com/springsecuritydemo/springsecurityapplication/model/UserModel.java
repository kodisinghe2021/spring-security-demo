package com.springsecuritydemo.springsecurityapplication.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
  @Id
  private String userName;
  private String email;
  private String name;
  private String password;
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
          name = "USER_ROLE",
          joinColumns = {
                  @JoinColumn(name = "USER_ID")
          },
          inverseJoinColumns = {
                  @JoinColumn(name = "ROLE_ID")
          }
  )
  private List<RoleModel> roleModelsList;

}
