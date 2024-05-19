package com.example.billpaymentsystemdemo.models;

import com.example.billpaymentsystemdemo.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users customer;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher; // Add this field to reference the Voucher entity

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private double amount;
    private double fee;

    // Getters and Setters
}

