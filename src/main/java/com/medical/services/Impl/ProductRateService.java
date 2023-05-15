package com.medical.services.Impl;

import com.medical.base.BasePagination;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.entity.Product;
import com.medical.entity.Rating;
import com.medical.entity.User;
import com.medical.forms.CreateProductRateForm;
import com.medical.repositories.IProductRateRepository;
import com.medical.repositories.IProductRepository;
import com.medical.repositories.IUserRepository;
import com.medical.services.IProductRateService;
import com.medical.specifications.GenericSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRateService extends BasePagination<Rating, IProductRateRepository> implements IProductRateService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IProductRateRepository productRateRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    public ProductRateService(IProductRateRepository productRateRepository){
        super(productRateRepository);
    }
    @Override
    public Rating findOne(GenericSpecification<Rating> specification) {
        return productRateRepository.findOne(specification).orElse(null);
    }

    @Override
    public Rating create(CreateProductRateForm createProductRateForm) {
        Product product = productRepository.findById(createProductRateForm.getProductId()).orElse(null);
        User user = userRepository.findById(createProductRateForm.getUserId()).orElse(null);

        Rating review = modelMapper.map(createProductRateForm, Rating.class);
        review.setProduct(product);
        review.setUser(user);

        return productRateRepository.save(review);
    }

    @Override
    public PaginateDTO<Rating> getList(Integer page, Integer perPage, GenericSpecification<Rating> specification) {
        return this.paginate(page, perPage, specification);
    }
}
