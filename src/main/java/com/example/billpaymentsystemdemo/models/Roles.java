package com.example.billpaymentsystemdemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "role_name")
  private String roleName;

  @Column(name = "description")
  private String description;

  @ManyToMany private List<Permissions> permissions;
}
