package com.example.billpaymentsystemdemo.controllers;


import com.example.billpaymentsystemdemo.dtos.requestDtos.ChangePasswordRequest;
import com.example.billpaymentsystemdemo.dtos.requestDtos.FirstSignInRequest;
import com.example.billpaymentsystemdemo.dtos.requestDtos.LoginRequest;
import com.example.billpaymentsystemdemo.dtos.responseDtos.CustomerResponseDto;
import com.example.billpaymentsystemdemo.dtos.responseDtos.LoginResponse;
import com.example.billpaymentsystemdemo.dtos.responseDtos.ResponseDto;
import com.example.billpaymentsystemdemo.services.UserService;
import com.example.billpaymentsystemdemo.models.SignUpRequest;
import com.example.billpaymentsystemdemo.util.PasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseDto<CustomerResponseDto> signUp(@RequestBody SignUpRequest request) {
    return userService.signUp(request);
  }


  @PostMapping("/login")
  public ResponseDto<LoginResponse> login(@RequestBody LoginRequest request) {
    return userService.login(request);
  }

  @PostMapping("/first-sign-in")
  public ResponseDto<String> changePasswordForFirstSignIn(@RequestBody FirstSignInRequest request) {
    return userService.changePasswordForFirstSignIn(request);
  }

  @PostMapping("/change-password")
  public ResponseDto<String> changePassword(@RequestBody ChangePasswordRequest request)
      throws PasswordException {
    return userService.changePassword(request);
  }
}
