package com.example.billpaymentsystemdemo.dtos.restData;

import com.example.billpaymentsystemdemo.util.Pagination;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaginationDto {

  private Integer page;
  private Integer size;
  private Sort.Direction direction;
  private String sortBy;

  public Pageable getPageable() {
    return Pagination.createPage(this);
  }
}
