package com.example.billpaymentsystemdemo.controllers;


import com.example.billpaymentsystemdemo.models.Permissions;
import com.example.billpaymentsystemdemo.services.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

  private final PermissionService permissionService;

  public PermissionController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  @PostMapping("/add")
  public void addRoles(String name) {
    permissionService.addPermission(name);
  }

  @GetMapping("/list")
  public List<Permissions> listRoles() {
    return permissionService.listAll();
  }
}
