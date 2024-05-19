package com.example.billpaymentsystemdemo.dtos.restData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherResponseDto {

  private Long id;
  private String voucherCode;
}
