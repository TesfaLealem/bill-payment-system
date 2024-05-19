package com.example.billpaymentsystemdemo.models;

import com.example.billpaymentsystemdemo.dtos.responseDtos.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


  CustomerResponseDto toDto(Users user);

  @Mapping(target = "role", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "contactInformation", ignore = true)
  @Mapping(target = "registeredOn", ignore = true)
  @Mapping(target = "lastLoggedIn", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "failedLoginAttempts", ignore = true)
  @Mapping(target = "pin", ignore = true)
  @Mapping(target = "isPhoneVerified", ignore = true)
  @Mapping(target = "temporaryPassword", ignore = true)
  @Mapping(target = "lockedTimeStamp", ignore = true)
  @Mapping(target = "lastPasswordChangedDate", ignore = true)
  @Mapping(target = "isPasswordChangeRequired", ignore = true)
  @Mapping(target = "phoneNumberVerifiedDate", ignore = true)
  Users toModel(SignUpRequest signUpRequest);


}
