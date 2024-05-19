package com.example.billpaymentsystemdemo.dtos.restData;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDetailsDto {
  private String id;
  private BigDecimal balance;
}
