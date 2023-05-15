package com.medical.services;

import com.medical.dto.create.CreateCategoryDTO;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.dto.update.UpdateCategoryDTO;
import com.medical.entity.Category;
import com.medical.specifications.GenericSpecification;

public interface ICategoryService {
    PaginateDTO<Category> getList(Integer page, Integer perPage, GenericSpecification<Category> specification);
    Category getCategoryById(Integer id);
    Category create(CreateCategoryDTO categoryDTO) throws Exception;

    Category update(UpdateCategoryDTO categoryDTO, Category currentCategory) throws Exception;

    void deleteById(Integer categoryId) throws Exception;

    void lockCategory(Integer id);

    void unLockCategory(Integer id);

    boolean existedByName(String name);
}
