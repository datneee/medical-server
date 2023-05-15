package com.medical.controllers;

import com.medical.base.BaseController;
import com.medical.dto.ProductDTO;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.entity.Product;
import com.medical.entity.Rating;
import com.medical.entity.User;
import com.medical.exceptions.NotFoundException;
import com.medical.filters.ProductRateFilter;
import com.medical.forms.CreateProductRateForm;
import com.medical.services.IProductRateService;
import com.medical.services.IProductService;
import com.medical.specifications.GenericSpecification;
import com.medical.specifications.SearchCriteria;
import com.medical.specifications.SearchOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-rates")
public class ProductRateController extends BaseController<Rating> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IProductRateService productRateService;

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> getListProductRatesByProductId(
            ProductRateFilter filter,
            HttpServletRequest request) {

        GenericSpecification<Rating> specification = new GenericSpecification<Rating>().getBasicQuery(request);
        specification.add(new SearchCriteria("product", filter.getProductId(), SearchOperation.EQUAL));

        PaginateDTO<Rating> paginateProductRates = productRateService.getList(filter.getPage(), filter.getPerPage(), specification);

        return this.resPagination(paginateProductRates);
    }

    @GetMapping("/detail")
    @PreAuthorize("@userAuthorizer.isClient(authentication)")
    public ResponseEntity<?> getProductRateByProductId(ProductRateFilter filter, HttpServletRequest request) {
        User requestedUser = (User) request.getAttribute("user");

        GenericSpecification<Rating> specification = new GenericSpecification<Rating>().getBasicQuery(request);
        specification.add(new SearchCriteria("product", filter.getProductId(), SearchOperation.EQUAL));
        specification.add(new SearchCriteria("user", requestedUser.getId(), SearchOperation.EQUAL));

        Rating productRate = productRateService.findOne(specification);

        return this.resSuccess(productRate);
    }

    @PostMapping
    @PreAuthorize("@userAuthorizer.isClient(authentication)")
    public ResponseEntity<?> createReview(@RequestBody @Valid CreateProductRateForm createProductRateForm, HttpServletRequest request) throws Exception {
        User requestUser = (User) request.getAttribute("user");

        ProductDTO productDTO = productService.getProductById(createProductRateForm.getProductId());
        if(productDTO == null)
            throw new NotFoundException("Not found this product");


        GenericSpecification<Rating> specification = new GenericSpecification<Rating>().getBasicQuery(request);
        specification.add((new SearchCriteria("product", createProductRateForm.getProductId(), SearchOperation.EQUAL)));
        specification.add(new SearchCriteria("user", createProductRateForm.getUserId(), SearchOperation.EQUAL));

        Rating review = productRateService.findOne(specification);

        if(review != null)
            throw new Exception("You has already review");

        createProductRateForm.setUserId(requestUser.getId());

        Rating newReview = productRateService.create(createProductRateForm);

        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
}
