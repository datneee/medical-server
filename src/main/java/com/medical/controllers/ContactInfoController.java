package com.medical.controllers;


import com.medical.services.IContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/contact")
@CrossOrigin("*")
public class ContactInfoController {
    @Autowired
    IContactInfoService contactInfoService;

    @PostMapping
    public ResponseEntity<?> createNewwContactInfo(@RequestParam(name = "email") String email, @RequestParam(name = "name") String name, @RequestParam(name = "phoneNumber") String phoneNumber, @RequestParam(name = "comments") String comments) {
        contactInfoService.createContactInfo(email, name, phoneNumber,comments);
        return new ResponseEntity<>("Thành công", HttpStatus.OK);
    }
}
