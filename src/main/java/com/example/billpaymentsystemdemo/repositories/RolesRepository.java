package com.example.billpaymentsystemdemo.repositories;


import com.example.billpaymentsystemdemo.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
  Roles findByRoleName(String name);
}
