package com.example.billpaymentsystemdemo.util;

import com.example.billpaymentsystemdemo.dtos.restData.PaginationDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pagination {

  private Pagination() {}

  public static Pageable createPage(PaginationDto reqDto) {
    String sortBy = reqDto.getSortBy() == null ? "id" : reqDto.getSortBy();
    Sort.Direction direction =
        reqDto.getDirection() == null ? Sort.Direction.DESC : reqDto.getDirection();
    Integer page = reqDto.getPage() == null ? 0 : reqDto.getPage();
    Integer size = reqDto.getSize() == null ? 10 : reqDto.getSize();

    // Set default sorting criteria
    Sort.Order order = new Sort.Order(direction, sortBy);

    // Check if custom sorting criteria and direction are provided
    if (sortBy.isEmpty()) {
      order = new Sort.Order(order.getDirection(), sortBy);
    }

    // Create PageRequest with page number, size, and sorting criteria
    return PageRequest.of(page, size, Sort.by(order));
  }
}
