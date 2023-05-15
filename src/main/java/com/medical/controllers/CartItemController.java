package com.medical.controllers;

import com.medical.constants.Common;
import com.medical.services.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartitem")
@CrossOrigin("*")
public class CartItemController {

    @Autowired
    private ICartItemService service;


    @GetMapping()
    public ResponseEntity<?> getCartItemById(@RequestParam(value = "id") Integer id) {
        return new ResponseEntity<>(service.getCartItemById(id), HttpStatus.OK);
    }
    @PutMapping(value = "/{id}/{Amount}")
    public ResponseEntity<?> updateAmountCartItems(@PathVariable("id") Integer id,
                                                   @PathVariable("Amount") Integer Amount){

        service.updateCartItemAmount(id , Amount);
        return new ResponseEntity<>(Common.MSG_UPDATED_AMOUNT_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCartItemById(@Param("id") Integer id ,@Param("userId") Integer userId){
        service.deleteById(id , userId);
        return new ResponseEntity<>(Common.MSG_DELETE_SUCCESS , HttpStatus.OK);
    }

    @DeleteMapping(value = "/list")
    public ResponseEntity<?> deleteListCartItemsById(@Param("ids") List<Integer> ids ,
                                                     @Param("userId") Integer userId){
        service.deleteByIdIn(ids,userId);
        return new ResponseEntity<>(Common.MSG_DELETE_SUCCESS , HttpStatus.OK);
    }


}
