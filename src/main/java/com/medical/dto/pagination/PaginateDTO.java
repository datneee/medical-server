package com.medical.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginateDTO<T> {
    private Page<T> pageData;
    private PaginationDTO.Pagination pagination;
}