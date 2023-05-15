package com.medical.controllers;


import com.medical.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/brands")
@CrossOrigin("*")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping()
    public ResponseEntity<?> getListBrands() {
        return new ResponseEntity<>(brandService.getListBrand(), HttpStatus.OK);
    }
}
