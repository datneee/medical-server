package com.medical.services.Impl;

import com.medical.constants.Common;
import com.medical.entity.Category;
import com.medical.entity.CategoryImage;
import com.medical.entity.ProductImages;
import com.medical.forms.CreateCategoryImageForm;
import com.medical.helpers.FileHelper;
import com.medical.repositories.ICategoryImageRepository;
import com.medical.services.ICategoryImageService;
import com.medical.services.IStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImageService implements ICategoryImageService {

    @Autowired
    private ICategoryImageRepository repository;

    @Autowired
    private IStorageService storageService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryImage> createCategoryImages(List<CreateCategoryImageForm> images, Category category) {
        List<CategoryImage> productImages = new ArrayList<>();
        for (CreateCategoryImageForm img:images) {
            CategoryImage image = img.toEntity();
            image.setCategory(category);
            productImages.add(image);
            repository.save(image);
        }
        return productImages;
    }

    @Override
    public void deleteByCategoryId(Integer categoryId) {
        repository.deleteByCategoryId(categoryId);
    }

    @Override
    public List<CategoryImage> createOrUpdateMany(Category category, MultipartFile[] files) {
        this.deleteByCategoryId(category.getId());
        storageService.deleteFilesByPrefix(String.valueOf(category.getId()), Common.CATEGORY_IMAGE_UPLOAD_PATH);
        List<CategoryImage> categoryImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String randomUniqueFileName = FileHelper.randomUniqueFileName(category.getId() + "-" + file.getOriginalFilename());
            String imageUrl = storageService.store(
                    Common.CATEGORY_IMAGE_UPLOAD_PATH,
                    file,
                    randomUniqueFileName
            );
            CategoryImage categoryImage = new CategoryImage(null, imageUrl, randomUniqueFileName, category, null, null);
            CategoryImage categoryImage1 = repository.save(categoryImage);
            categoryImages.add(categoryImage1);
        }
        return categoryImages;
    }
}
