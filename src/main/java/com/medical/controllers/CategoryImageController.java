package com.medical.controllers;

import com.medical.base.BaseController;
import com.medical.entity.Category;
import com.medical.entity.CategoryImage;
import com.medical.entity.Product;
import com.medical.entity.ProductImages;
import com.medical.exceptions.AppException;
import com.medical.exceptions.NotFoundException;
import com.medical.helpers.FileHelper;
import com.medical.services.ICategoryImageService;
import com.medical.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/v1/category-images")
public class CategoryImageController extends BaseController<CategoryImage> {

    @Autowired
    private ICategoryImageService categoryImageService;

    @Autowired
    private ICategoryService categoryService;


    @PostMapping
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createOrUpdateCategoryImages(@RequestParam("categoryId") Integer categoryId,
                                                         @RequestParam("files") MultipartFile[] files) {

        for (MultipartFile file : files) {
            if (!FileHelper.isAllowImageType(file.getOriginalFilename())) {
                throw new AppException("This file type is not allowed");
            }
        }

        Category category = categoryService.getCategoryById(categoryId);

        if (category == null) {
            throw new NotFoundException("Not found category");
        }

        List<CategoryImage> categoryImages = categoryImageService.createOrUpdateMany(category, files);

        return this.resListSuccess(categoryImages);
    }
}
