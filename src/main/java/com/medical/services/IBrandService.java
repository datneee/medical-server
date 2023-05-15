package com.medical.services;

import com.medical.entity.Brand;

import java.util.List;

public interface IBrandService {

    List<Brand> getListBrand();

    Brand getBrandById(Integer id);
}
