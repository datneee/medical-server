package com.medical.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDTO<T> {
    private Integer status;
    private String message;
    private PaginationDTO<T> result;
}
