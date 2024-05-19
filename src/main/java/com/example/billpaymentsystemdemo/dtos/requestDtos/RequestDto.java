package com.example.billpaymentsystemdemo.dtos.requestDtos;

import com.example.billpaymentsystemdemo.dtos.restData.PaginationDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestDto<T> {
  private T data;
  private PaginationDto pagination;
}
