package com.medical.controllers;

import com.medical.base.BaseController;
import com.medical.dto.ProductDTO;
import com.medical.entity.Product;
import com.medical.entity.ProductImages;
import com.medical.exceptions.AppException;
import com.medical.exceptions.NotFoundException;
import com.medical.helpers.FileHelper;
import com.medical.services.IProductImageService;
import com.medical.services.IProductService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/v1/product-images")
public class ProductImageController extends BaseController<ProductImages> {
    @Autowired
    private IProductImageService productImageService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createOrUpdateProductImages(@RequestParam("productId") Integer productId,
                                                         @RequestParam("files") MultipartFile[] files) {

        for (MultipartFile file : files) {
            if (!FileHelper.isAllowImageType(file.getOriginalFilename())) {
                throw new AppException("This file type is not allowed");
            }
        }

        ProductDTO productDTO = productService.getProductById(productId);

        if (productDTO == null) {
            throw new NotFoundException("Not found product");
        }
        Product product = modelMapper.map(productDTO, Product.class);
        List<ProductImages> productImages = productImageService.createOrUpdateMany(product, files);

        return this.resListSuccess(productImages);
    }
}