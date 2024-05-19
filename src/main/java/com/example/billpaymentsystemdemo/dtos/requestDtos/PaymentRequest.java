package com.example.billpaymentsystemdemo.dtos.requestDtos;


import com.example.billpaymentsystemdemo.models.Payment;
import java.util.List;

public class PaymentRequest {
    private Long customerId;
    private List<Payment> payments;

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
