package com.medical.controllers;

import com.medical.base.BaseController;
import com.medical.constants.Common;
import com.medical.dto.ProductDTO;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.dto.pagination.PaginationDTO;
import com.medical.entity.Product;
import com.medical.exceptions.NotFoundException;
import com.medical.filters.ProductFilter;
import com.medical.forms.CreateProductForm;
import com.medical.forms.UpdateProductForm;
import com.medical.services.IProductService;
import com.medical.specifications.GenericSpecification;
import com.medical.specifications.SearchCriteria;
import com.medical.specifications.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/products")
@CrossOrigin("*")
public class ProductController extends BaseController<Product> {

    @Autowired
    private IProductService service;



    @GetMapping
    public ResponseEntity<?> getAllProducts(ProductFilter productFilter, HttpServletRequest request){
        GenericSpecification<Product> specification = new GenericSpecification<Product>().getBasicQuery(request);


        if(productFilter.getStartId() != null)
            specification.add(new SearchCriteria("id", productFilter.getStartId(), SearchOperation.GREATER_THAN_EQUAL));
        if(productFilter.getEndId() != null)
            specification.add(new SearchCriteria("id", productFilter.getEndId(), SearchOperation.LESS_THAN_EQUAL));
        // Tìm kiếm
        if(productFilter.getSearch() != null) {
            specification.add(new SearchCriteria("title", productFilter.getSearch(), SearchOperation.LIKE));
        }
        if (productFilter.getCategoryId() != null) {
            specification.add(new SearchCriteria("category", productFilter.getCategoryId(), SearchOperation.EQUAL));
        }

        if(productFilter.getMnOPrice() != null)
            specification.add(new SearchCriteria("originalPrice", productFilter.getMnOPrice(), SearchOperation.GREATER_THAN_EQUAL));

        if(productFilter.getMxOPrice() != null)
            specification.add(new SearchCriteria("originalPrice", productFilter.getMxOPrice(), SearchOperation.LESS_THAN_EQUAL));


        PaginateDTO<Product> paginateProducts = service.getAllProducts(productFilter.getPage(), productFilter.getPerPage(), specification);
        return this.resPagination(paginateProducts);
    }

    @GetMapping(value = "/feature")
    public ResponseEntity<?> getListFeatureProduct() {
        return new ResponseEntity<>(service.getListFeatureProduct(), HttpStatus.OK);
    }
    @GetMapping(value = "/inCategory")
    @ResponseBody
    public ResponseEntity<?> getProductByCategoryIdId(@RequestParam(name = "id") Integer id, ProductFilter productFilter){
        List<Product> products  = service.getProductByCategoryId(id);
        if(products == null)
            throw new NotFoundException(Common.MSG_NOT_FOUND);
        return this.resSuccess(products);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Integer id){
        ProductDTO product  = service.getProductById(id);
        if(product == null)
            throw new NotFoundException(Common.MSG_NOT_FOUND);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @GetMapping(value = "/title/{title}")
    public ResponseEntity<?> getProductByTitle(@PathVariable("title") String title){
        Product product  = service.getProductByTitle(title);
        if(product == null)
            throw new NotFoundException(Common.MSG_NOT_FOUND);
        return this.resSuccess(product);
    }
    @PutMapping(value = "/{id}/addTicket")
    public ResponseEntity<?> addTicketToProduct(@PathVariable("id") Integer id, @RequestParam(name = "ticket") Integer ticket) throws Exception {
        return new ResponseEntity<>(service.addTicket(id, ticket), HttpStatus.OK);
    }
    @GetMapping(value = "/special")
    public ResponseEntity<?> getListSpecialProduct() {
        return new ResponseEntity<>(service.getListSpecialProduct(), HttpStatus.OK);
    }
    @GetMapping(value = "/existsTitle/{title}")
    public ResponseEntity<?> existedByProductTitle(@PathVariable("title") String title){
        return new ResponseEntity<>(service.existsProductByTitle(title) , HttpStatus.OK);
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<?> buyProduct(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "productId") Integer productId, @RequestParam(name = "amount") Integer amount, @RequestParam(name = "payment") String payment, @RequestParam(name = "shipAddress") String shipAddress){
        service.buyOneItem(userId, productId, amount, payment, shipAddress);
        return new ResponseEntity<>(Common.MSG_SUCCESS, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductForm form) throws MessagingException, IOException {
        return new ResponseEntity<>(service.createProduct(form),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id , @RequestBody UpdateProductForm form){
        service.updateProduct(id,form);
        return new ResponseEntity<>("updated Successful",HttpStatus.OK);
    }


    @PutMapping(value = "/unlock/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> unLockProduct(@PathVariable("id") Integer id){
        service.unLockProductStatus(id);
        return new ResponseEntity<>("Unlock Product Successfull",HttpStatus.OK);
    }

    @PutMapping(value = "/lock/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> lockProduct(@PathVariable("id") Integer id){
        service.lockProductStatus(id);
        return new ResponseEntity<>("Lock Product Successfull",HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        service.deleteProduct(id);
        return new ResponseEntity<>("Delete Product Successfull",HttpStatus.OK);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> getProductCount(){
        return new ResponseEntity<>(service.getProductCount() , HttpStatus.OK);
    }






}
