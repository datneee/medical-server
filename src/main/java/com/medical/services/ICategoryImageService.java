package com.medical.services;

import com.medical.entity.Category;
import com.medical.entity.CategoryImage;
import com.medical.forms.CreateCategoryImageForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryImageService {
    List<CategoryImage> createCategoryImages(List<CreateCategoryImageForm> images , Category category);

    void deleteByCategoryId(Integer categoryId);

    List<CategoryImage> createOrUpdateMany(Category category, MultipartFile[] files);
}
