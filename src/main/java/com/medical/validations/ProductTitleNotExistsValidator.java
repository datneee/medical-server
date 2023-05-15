package com.medical.validations;

import com.medical.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ProductTitleNotExistsValidator implements ConstraintValidator<ProductTitleNotExists , String> {

    @Autowired
    private IProductService service;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value))
            return true;
        return !service.existsProductByTitle(value);

    }
}
