package com.medical.services.Impl;

import com.medical.constants.Common;
import com.medical.entity.Product;
import com.medical.entity.ProductImages;
import com.medical.forms.CreateProductImageForm;
import com.medical.helpers.FileHelper;
import com.medical.repositories.IProductImagesRepository;
import com.medical.services.IProductImageService;
import com.medical.services.IStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageService implements IProductImageService {

    @Autowired
    private IProductImagesRepository repository;

    @Autowired
    private IStorageService storageService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ProductImages> createProductImages(List<CreateProductImageForm> images , Product product) {
        List<ProductImages> productImages = new ArrayList<>();
        for (CreateProductImageForm img:images) {
            ProductImages image = img.toEntity();
            image.setProduct(product);
            productImages.add(image);
            repository.save(image);
        }
        return productImages;
    }

    @Override
    public void deleteByProductId(Integer productId) {
        repository.deleteByProductId(productId);
    }

    @Override
    public List<ProductImages> createOrUpdateMany(Product product, MultipartFile[] files) {
        this.deleteByProductId(product.getId());
        storageService.deleteFilesByPrefix(String.valueOf(product.getId()), Common.PRODUCT_IMAGE_UPLOAD_PATH);
        List<ProductImages> productImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String randomUniqueFileName = FileHelper.randomUniqueFileName(product.getId() + "-" + file.getOriginalFilename());
            String imageUrl = storageService.store(
                    Common.PRODUCT_IMAGE_UPLOAD_PATH,
                    file,
                    randomUniqueFileName
            );
            ProductImages productImages1 = new ProductImages(null, imageUrl, randomUniqueFileName, product, null, null);
            ProductImages newProductImages = repository.save(productImages1);
            productImages.add(newProductImages);
        }
        return productImages;
    }
}
