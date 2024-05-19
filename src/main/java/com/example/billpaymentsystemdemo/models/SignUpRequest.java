package com.example.billpaymentsystemdemo.models;

import com.example.billpaymentsystemdemo.enums.GenderEnum;
import com.example.billpaymentsystemdemo.enums.UserStatusEnum;
import com.example.billpaymentsystemdemo.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SignUpRequest {
  private String username;
  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private Long role;
  private UserTypeEnum userTypeEnum;
  private GenderEnum genderEnum;
  private UserStatusEnum userStatusEnum;
  private Date dateOfBirth;
}
