package com.example.billpaymentsystemdemo.repositories;

import com.example.billpaymentsystemdemo.enums.UserTypeEnum;
import com.example.billpaymentsystemdemo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
  Users findByUsername(String username);

  Users findByPhoneNumber(String phone);

  boolean existsByPhoneNumber(String phone);

  boolean existsByUsername(String username);

  List<Users> findAllByUserTypeEnum(UserTypeEnum userTypeEnum);
}
