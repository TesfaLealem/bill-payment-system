package com.example.billpaymentsystemdemo.util;

public class PhoneNumberStandardize {
  private PhoneNumberStandardize() {}

  public static String standardizePhoneNumber(String phoneNumber) {
    // Remove all non-digit characters from the phone number
    String digitsOnly = phoneNumber.replaceAll("\\D", "");

    // Remove the leading "0" from the phone number if present
    if (digitsOnly.startsWith("0")) {
      digitsOnly = digitsOnly.substring(1);
    }

    // Add the country code "+251" to the phone number

    return "+251" + digitsOnly;
  }
}
