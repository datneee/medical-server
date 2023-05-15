package com.medical.filters;

import com.medical.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFilter {

    private Integer categoryId;

    private Integer startId;

    private Integer endId;

    private String search;

    private Integer page;

    private Integer perPage;

    private Integer mnOPrice;

    private Integer mxOPrice;

    private Integer mnRate;

    private Integer mxRate;
}
