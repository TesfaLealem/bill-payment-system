package com.example.billpaymentsystemdemo.dtos.restData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AddRoleDto {
  private String name;
  private List<Long> permissions;
}
