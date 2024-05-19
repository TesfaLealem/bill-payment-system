package com.example.billpaymentsystemdemo.dtos.restData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListPayment {

  private Long id;
  private Long voucherId;
  private String voucherCode;
  private String paymentType;
  private double amount;
  private double fee;


}
