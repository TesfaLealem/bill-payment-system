package com.example.billpaymentsystemdemo.controllers;


import com.example.billpaymentsystemdemo.dtos.requestDtos.UpdatePrivilegesRequest;
import com.example.billpaymentsystemdemo.dtos.responseDtos.ResponseDto;
import com.example.billpaymentsystemdemo.dtos.restData.AddRoleDto;
import com.example.billpaymentsystemdemo.models.Roles;
import com.example.billpaymentsystemdemo.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
  private final RoleService roleService;

  @PostMapping("/add")
  public ResponseDto<Roles> addRoles(@RequestBody AddRoleDto addRoleDto) {
    return roleService.addRole(addRoleDto);
  }

  @GetMapping("/list")
  public List<Roles> listRoles() {
    return roleService.rolesList();
  }

  //    @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/update_privileges")
  public ResponseDto<String> updateRolePrivileges(@RequestBody UpdatePrivilegesRequest request) {

    roleService.updateRolePrivileges(
        request.getRoleId(), request.getPermissionsToAdd(), request.getPermissionsToRemove());
    return ResponseDto.success("Role privileges updated successfully.");
  }
}
