package com.example.billpaymentsystemdemo.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResDto {
  private String accessToken;
  private String refreshToken;
}
