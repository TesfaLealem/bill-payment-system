package com.example.billpaymentsystemdemo.dtos.responseDtos;

import com.example.billpaymentsystemdemo.dtos.restData.AccountDetailsDto;
import com.example.billpaymentsystemdemo.enums.GenderEnum;
import com.example.billpaymentsystemdemo.enums.UserStatusEnum;
import com.example.billpaymentsystemdemo.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class CustomerResponseDto {

  private Long id;
  private String contactInformation;
  private LocalDateTime registeredOn;
  private UserStatusEnum status;
  private String firstName;
  private String lastName;
  private String middleName;
  private GenderEnum gender;
  private Date dateOfBirth;
  private String email;
  private String phoneNumber;
  private UserTypeEnum userTypeEnum;
  private List<AccountDetailsDto> accounts;
}
