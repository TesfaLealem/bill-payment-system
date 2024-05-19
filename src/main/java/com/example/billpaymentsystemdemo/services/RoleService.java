package com.example.billpaymentsystemdemo.services;
import com.example.billpaymentsystemdemo.dtos.responseDtos.ResponseDto;
import com.example.billpaymentsystemdemo.dtos.restData.AddRoleDto;
import com.example.billpaymentsystemdemo.models.Permissions;
import com.example.billpaymentsystemdemo.models.Roles;
import com.example.billpaymentsystemdemo.repositories.PermissionsRepository;
import com.example.billpaymentsystemdemo.repositories.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
  private final RolesRepository roleRepository;
  private final PermissionsRepository permissionRepository;

  public RoleService(RolesRepository roleRepository, PermissionsRepository permissionRepository) {
    this.roleRepository = roleRepository;
    this.permissionRepository = permissionRepository;
  }

  public ResponseDto<Roles> addRole(AddRoleDto addRoleDto) {
    Roles byRoleName = roleRepository.findByRoleName(addRoleDto.getName());
    if (byRoleName != null) {
      return ResponseDto.error("Role Already Exist");
    }
    Roles role = new Roles();
    role.setRoleName(addRoleDto.getName());

    List<Permissions> permissions = permissionRepository.findAllById(addRoleDto.getPermissions());
    role.setPermissions(permissions);

    Roles save = roleRepository.save(role);
    return ResponseDto.data(save, "Role Added Successfully");
  }

  public List<Roles> rolesList() {
    return roleRepository.findAll();
  }

  public Roles findByID(Long id) {
    return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
  }

  public void updateRolePrivileges(
      Long roleId, List<Long> permissionIdsToAdd, List<Long> permissionIdsToRemove) {
    Roles role =
        roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

    List<Permissions> permissionsToAdd = permissionRepository.findAllById(permissionIdsToAdd);
    List<Permissions> permissionsToRemove = permissionRepository.findAllById(permissionIdsToRemove);

    role.getPermissions().addAll(permissionsToAdd);
    role.getPermissions().removeAll(permissionsToRemove);

    roleRepository.save(role);
  }
}
