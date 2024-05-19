package com.example.billpaymentsystemdemo.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto<T> {

  private boolean success;
  private String message;
  private T data;
  @JsonIgnore private T error;

  public ResponseDto() {}

  public ResponseDto(boolean success, String message, T data) {
    this.success = success;
    this.message = message;
    this.data = data;
  }


  public ResponseDto(boolean success, String message, T data, T error) {
    this.success = success;
    this.message = message;
    this.data = data;
    this.error = error;
  }

  // Getters and setters omitted for brevity

  public static <T> ResponseDto<T> success(String message) {
    return new ResponseDto<>(true, message, null);
  }

  public static <T> ResponseDto<T> data(T data) {
    return new ResponseDto<>(true, null, data);
  }

  public static <T> ResponseDto<T> data(T data, String message) {
    return new ResponseDto<>(true, message, data);
  }

  public static <T> ResponseDto<T> error(String message) {
    return new ResponseDto<>(false, message, null);
  }

  public static <T> ResponseDto<T> error(String message, T errors) {
    return new ResponseDto<>(false, message, null, errors);
  }

  /*
  public boolean isSuccess() {
      return success;
  }

  public void setSuccess(boolean success) {
      this.success = success;
  }

  public String getMessage() {
      return message;
  }

  public void setMessage(String message) {
      this.message = message;
  }

  public T getData() {
      return data;
  }

  public void setData(T data) {
      this.data = data;
  }*/
}
