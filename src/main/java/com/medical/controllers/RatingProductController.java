package com.medical.controllers;


import com.medical.base.BaseController;
import com.medical.entity.Rating;
import com.medical.services.IRatingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rates")
@CrossOrigin("*")
public class RatingProductController extends BaseController<Rating> {

    @Autowired
    IRatingProductService productService;
    @GetMapping
    public ResponseEntity<?> getListCommentProduct() {
        return this.resSuccess(productService.getAllComments());
    }

    @PostMapping
    @PreAuthorize("@userAuthorizer.isYourself(authentication, #userId)")
    public ResponseEntity<?> createCommentProduct(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "productId") Integer productId, @RequestParam(name = "comment") String comment) {
        return this.resSuccess(productService.createComment(userId, productId, comment));
    }

}
