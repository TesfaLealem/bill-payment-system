package com.example.billpaymentsystemdemo.security;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SignatureAlgorithm {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Bean
  public Algorithm get() {
    return Algorithm.HMAC256(jwtSecret);
  }
}
