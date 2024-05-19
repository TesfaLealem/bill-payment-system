package com.example.billpaymentsystemdemo.services;


import com.example.billpaymentsystemdemo.dtos.requestDtos.ChangePasswordRequest;
import com.example.billpaymentsystemdemo.dtos.requestDtos.FirstSignInRequest;
import com.example.billpaymentsystemdemo.dtos.requestDtos.LoginRequest;
import com.example.billpaymentsystemdemo.dtos.responseDtos.CustomerResponseDto;
import com.example.billpaymentsystemdemo.dtos.responseDtos.LoginResponse;
import com.example.billpaymentsystemdemo.dtos.responseDtos.ResponseDto;
import com.example.billpaymentsystemdemo.security.JwtUtils;
import com.example.billpaymentsystemdemo.security.UserDetailsServiceImpl;
import com.example.billpaymentsystemdemo.dtos.restData.UserDetailsImpl;
import com.example.billpaymentsystemdemo.util.PhoneNumberStandardize;
import com.example.billpaymentsystemdemo.models.*;
import com.example.billpaymentsystemdemo.enums.UserStatusEnum;
import com.example.billpaymentsystemdemo.enums.UserTypeEnum;
import com.example.billpaymentsystemdemo.repositories.SmsRepository;
import com.example.billpaymentsystemdemo.repositories.UserRepository;
import com.example.billpaymentsystemdemo.util.PasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final RoleService roleService;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl userDetailsService;
  private final SmsRepository smsRepository;
  private final UserMapper userMapper;

  public ResponseDto<CustomerResponseDto> signUp(SignUpRequest request) {
    boolean byUsername = userRepository.existsByUsername(request.getUsername());
    String standardizePhoneNumber =
        PhoneNumberStandardize.standardizePhoneNumber(request.getPhoneNumber());
    boolean byPhoneNumber = userRepository.existsByPhoneNumber(standardizePhoneNumber);
    if (byPhoneNumber) {
      return ResponseDto.error("Phone Number Already Exist");
    }
    if (byUsername) {
      return ResponseDto.error("Username Already Exist");
    } else {
      Users user = userMapper.toModel(request);
      user.setPhoneNumber(standardizePhoneNumber);
      user.setStatus(UserStatusEnum.PENDING);
      Roles byID = roleService.findByID(request.getRole());
      user.setRole(byID);
      UserTypeEnum userTypeEnum = UserTypeEnum.valueOf(request.getUserTypeEnum().name());
      user.setUserTypeEnum(request.getUserTypeEnum());
      String s = generateTempPassword(6);
      user.setTemporaryPassword(passwordEncoder.encode(s));

      SMS sms = new SMS(null, s, "Your OTP for " + user.getUsername() + " is ");
      smsRepository.save(sms);
      userRepository.save(user);



      Users save1 = userRepository.save(user);
      CustomerResponseDto dto = userMapper.toDto(save1);
      return ResponseDto.data(dto, "User Registered Successfully");
    }
  }


  public ResponseDto<LoginResponse> login(LoginRequest loginRequest) {
    UserDetails userDetails1 = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    Users byUsername = userRepository.findByUsername(loginRequest.getUsername());
    if (isFirstSignIn(byUsername.getUsername())) {
      return ResponseDto.error("First sign-in: Change password");
    }
    if (byUsername.getStatus().equals(UserStatusEnum.LOCKED)) {
      return ResponseDto.error("User Is Locked because of multiple retry");
    }
    if (!byUsername.getUsername().equals(loginRequest.getUsername())) {
      return ResponseDto.error("UserName doesn't exist");
    }
    boolean matches = passwordEncoder.matches(loginRequest.getPassword(), byUsername.getPassword());
    if (matches) {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(),
                  loginRequest.getPassword(),
                  userDetails1.getAuthorities()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);
      byUsername.setFailedLoginAttempts(0);
      byUsername.setLastLoggedIn(LocalDateTime.now());
      userRepository.save(byUsername);
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      return ResponseDto.data(new LoginResponse(userDetails.getUsername(), jwt));
    } else {
      int attempts = byUsername.getFailedLoginAttempts() + 1;
      byUsername.setFailedLoginAttempts(attempts);
      if (attempts > 3) {
        byUsername.setStatus(UserStatusEnum.LOCKED);
        byUsername.setLockedTimeStamp(new Date());
      }
      userRepository.save(byUsername);
      return ResponseDto.error("Incorrect Password Please Try Again");
    }
  }

  public boolean isFirstSignIn(String username) {
    Users user = userRepository.findByUsername(username);

    if (user != null) {
      return user.getTemporaryPassword() != null;
    }

    return false;
  }

  public ResponseDto<String> changePasswordForFirstSignIn(FirstSignInRequest request) {
    Users user = userRepository.findByUsername(request.getUsername());

    if (!request.getNewPassword().equals(request.getConfirmPassword())) {
      throw new PasswordException("Confirm Password don't match");
    } else if (passwordEncoder.matches(request.getOldPassword(), user.getTemporaryPassword())) {
      user.setPassword(passwordEncoder.encode(request.getNewPassword()));
      user.setTemporaryPassword(null);
      user.setStatus(UserStatusEnum.ACTIVE);
      user.setIsPasswordChangeRequired(false);
      user.setLastPasswordChangedDate(new Date());
      userRepository.save(user);
      return ResponseDto.success("Password Changed");
    } else {
      throw new PasswordException("Old Password Not Correct");
    }
  }

  public ResponseDto<String> changePassword(ChangePasswordRequest request)
      throws PasswordException {
    Users user = new Users();
    if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
      throw new PasswordException("Incorrect Old Password");
    } else if (!request.getNewPassword().equals(request.getConfirmPassword())) {
      throw new PasswordException("Confirm Password don't match");
    } else {
      user.setPassword(passwordEncoder.encode(request.getNewPassword()));
      user.setTemporaryPassword(null);
      user.setLastPasswordChangedDate(new Date());
      userRepository.save(user);
      return ResponseDto.success("Password Changed");
    }
  }

  public String generateTempPassword(Integer passwordLength) {
    final String characters = "0123456789";
    passwordLength = passwordLength != null ? passwordLength : 6; // Updated default length to 6
    Random random = new SecureRandom();
    StringBuilder sb = new StringBuilder(passwordLength);
    for (int i = 0; i < passwordLength; i++) {
      sb.append(characters.charAt(random.nextInt(characters.length())));
    }
    return sb.toString();
  }
}
