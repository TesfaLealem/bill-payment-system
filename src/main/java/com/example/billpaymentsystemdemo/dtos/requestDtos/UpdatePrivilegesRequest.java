package com.example.billpaymentsystemdemo.dtos.requestDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdatePrivilegesRequest {
  private Long roleId;
  private List<Long> permissionsToAdd;
  private List<Long> permissionsToRemove;
}
