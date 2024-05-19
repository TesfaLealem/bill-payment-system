package com.example.billpaymentsystemdemo.enums;

public enum UserStatusEnum {
  ACTIVE("Active", "active"),
  PENDING("PENDING", "pending"),
  INACTIVE("Inactive", "inactive"),
  LOCKED("Locked", "locked");
  private final String value;
  private final String description;

  UserStatusEnum(String value, String description) {
    this.value = value;
    this.description = description;
  }

  public String getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }

  // Optional: Override toString() method to return description
  @Override
  public String toString() {
    return value;
  }
}
