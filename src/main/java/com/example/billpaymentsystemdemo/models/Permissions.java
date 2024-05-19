package com.example.billpaymentsystemdemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "permissions")
public class Permissions extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "description")
  private String description;

  public Permissions(String description) {
    this.description = description;
  }
  //    @ManyToMany
  //    private List<Roles> roles;
}
