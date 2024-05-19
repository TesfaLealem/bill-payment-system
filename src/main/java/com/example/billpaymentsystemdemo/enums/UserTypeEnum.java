package com.example.billpaymentsystemdemo.enums;

public enum UserTypeEnum {

  CUSTOMER("Customer", "customer"),
  ADMIN("Admin", "admin");

  private final String value;
  private final String description;

  UserTypeEnum(String value, String description) {
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
    return description;
  }
}
