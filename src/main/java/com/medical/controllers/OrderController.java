package com.medical.controllers;

import com.medical.entity.Order;
import com.medical.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService service;

    @GetMapping()
    public ResponseEntity<?> getAllOrder() {
        return new ResponseEntity<>(service.getListOrders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable("userId") Integer userId){
        List<Order> orders = service.getOrdersByUserId(userId);
        return  new ResponseEntity<>(orders , HttpStatus.OK);
    }
}
