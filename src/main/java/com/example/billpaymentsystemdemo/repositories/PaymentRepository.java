package com.example.billpaymentsystemdemo.repositories;


import com.example.billpaymentsystemdemo.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}


