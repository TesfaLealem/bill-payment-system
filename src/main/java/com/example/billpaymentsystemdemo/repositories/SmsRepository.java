package com.example.billpaymentsystemdemo.repositories;


import com.example.billpaymentsystemdemo.models.SMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SMS, Long> {}
