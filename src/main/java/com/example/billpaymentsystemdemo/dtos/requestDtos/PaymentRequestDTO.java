package com.example.billpaymentsystemdemo.dtos.requestDtos;


import com.example.billpaymentsystemdemo.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private PaymentType paymentType;
    private double amount;
    private double fee;
}
