package com.example.billpaymentsystemdemo.repositories;


import com.example.billpaymentsystemdemo.models.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {}
