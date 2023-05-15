package com.medical.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationDTO<T> {
    private T data;
    private Pagination pagination;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Pagination {
        private Integer page;
        private Integer perPage;
        private Integer lastPage;
        private Long total;
    }
}