package com.medical.services;

import com.medical.dto.pagination.PaginateDTO;
import com.medical.entity.Rating;
import com.medical.forms.CreateProductRateForm;
import com.medical.specifications.GenericSpecification;

public interface IProductRateService {
    Rating findOne(GenericSpecification<Rating> specification);

    Rating create(CreateProductRateForm createProductRateForm);

    PaginateDTO<Rating> getList(Integer page, Integer perPage, GenericSpecification<Rating> specification);
}
