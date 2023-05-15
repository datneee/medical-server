package com.medical.controllers;

import com.medical.constants.Common;
import com.medical.entity.Cart;
import com.medical.filters.AddCartParams;
import com.medical.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/carts")
@CrossOrigin("*")
public class CartController {


    @Autowired
    private ICartService service;



    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCartByUserId(@PathVariable("id") Integer id){
        Cart cart = service.getCartByUserId(id);
//        cart = service.updateCartAmount(cart.getCartItemList().toArray().length , cart);
        return new ResponseEntity<>(cart , HttpStatus.OK);
    }


    @PostMapping(value = "/addCartItem")
    public ResponseEntity<?> addCartItemToCart(@RequestBody AddCartParams params) throws Exception {
        try{
            service.addCartItemToCart(params);
            return new ResponseEntity<>("add to cart successful", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/buyListCartItems/{userId}")
    public ResponseEntity<?> buyListCartItems(@PathVariable("userId") Integer userId,@RequestParam(name = "payment") String payment, @RequestParam(name = "shipAddress") String shipAddress){
        service.buyListCartItems(userId, payment, shipAddress);
        return new ResponseEntity<>(Common.MSG_BUY_ALL_CART_ITEMS_SUCCESS, HttpStatus.OK);
    }

}

