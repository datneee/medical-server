package com.medical.services.Impl;

import com.medical.constants.Common;
import com.medical.entity.Product;
import com.medical.entity.Rating;
import com.medical.entity.User;
import com.medical.exceptions.NotFoundException;
import com.medical.repositories.IProductRepository;
import com.medical.repositories.IRatingProductRepository;
import com.medical.repositories.IUserRepository;
import com.medical.services.IRatingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingProductService implements IRatingProductService {

    @Autowired
    IRatingProductRepository ratingProductRepository;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IUserRepository userRepository;
    @Override
    public List<Rating> getAllComments() {
        return ratingProductRepository.findAll();
    }

    @Override
    public Rating createComment(Integer userId, Integer productId, String comment) {
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            throw  new NotFoundException(Common.MSG_NOT_FOUND);
        }
        User user = userRepository.getById(userId);
        if (user == null) {
            throw  new NotFoundException(Common.MSG_NOT_FOUND);
        }
        Rating rating = null;
        if (comment.trim() != null) {
            rating = new Rating(user, product, comment.trim());
        }
        return ratingProductRepository.save(rating);

    }
}
