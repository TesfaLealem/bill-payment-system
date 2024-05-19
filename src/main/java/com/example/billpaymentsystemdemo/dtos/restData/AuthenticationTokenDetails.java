package com.example.billpaymentsystemdemo.dtos.restData;

import com.example.billpaymentsystemdemo.models.Permissions;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class AuthenticationTokenDetails {
  private String id;
  private String username;
  private List<Permissions> roles;
  private Date issuedDate;
  private Date expirationDate;
  private String issuer;
}
