package com.example.billpaymentsystemdemo.models;

import com.example.billpaymentsystemdemo.enums.GenderEnum;
import com.example.billpaymentsystemdemo.enums.UserStatusEnum;
import com.example.billpaymentsystemdemo.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password")
  @JsonIgnore
  private String password;

  @Column(name = "contact_information")
  private String contactInformation;

  @Column(name = "registrered_on")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private LocalDateTime registeredOn;

  @Column(name = "last_logged_in")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime lastLoggedIn;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 50)
  private UserStatusEnum status;

  @Column(name = "first_name", length = 255)
  private String firstName;

  @Column(name = "last_name", length = 255)
  private String lastName;

  @Column(name = "middel_name", length = 255)
  private String middleName;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", length = 255)
  private GenderEnum gender;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_of_birth")
  private Date dateOfBirth;

  @Column(name = "email", unique = true, length = 255)
  private String email;

  @Column(name = "phone_number", unique = true)
  private String phoneNumber;

  @Column(name = "failed_login_attempts")
  private Integer failedLoginAttempts;

  @Column(name = "pin")
  private String pin;

  @Column(name = "is_phone_verified")
  private Boolean isPhoneVerified;

  @Column(name = "temporary_password")
  private String temporaryPassword;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lockedTimeStamp;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastPasswordChangedDate;

  private Boolean isPasswordChangeRequired;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.TIMESTAMP)
  private Date phoneNumberVerifiedDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_type")
  private UserTypeEnum userTypeEnum;


  @OneToMany(mappedBy = "user")
  private List<Voucher> vouchers;

  @ManyToOne private Roles role;
}
