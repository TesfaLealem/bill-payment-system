package com.example.billpaymentsystemdemo.dtos.requestDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FirstSignInRequest {
  private String username;
  private String newPassword;
  private String confirmPassword;
  private String oldPassword;
}
