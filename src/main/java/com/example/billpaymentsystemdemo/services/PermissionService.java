package com.example.billpaymentsystemdemo.services;

import com.example.billpaymentsystemdemo.models.Permissions;
import com.example.billpaymentsystemdemo.repositories.PermissionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
  private final PermissionsRepository permissionRepository;

  public PermissionService(PermissionsRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }

  public void addPermission(String name) {
    Permissions permission = new Permissions(name);
    permission.setDescription(name);
    permissionRepository.save(permission);
  }

  public List<Permissions> listAll() {
    return permissionRepository.findAll();
  }
}
