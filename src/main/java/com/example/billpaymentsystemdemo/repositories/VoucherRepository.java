package com.example.billpaymentsystemdemo.repositories;


import com.example.billpaymentsystemdemo.models.Payment;
import com.example.billpaymentsystemdemo.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Optional<Voucher> findByVoucherCode(String voucherCode);
}

