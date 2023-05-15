package com.medical.services;

import com.medical.entity.Rating;

import java.util.List;

public interface IRatingProductService {

    List<Rating> getAllComments();

    Rating createComment(Integer userId, Integer productId, String comment);

}
