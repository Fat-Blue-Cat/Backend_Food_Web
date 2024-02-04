package com.spring.foodWeb.controller;

import com.spring.foodWeb.response.ResponseData;
import com.spring.foodWeb.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuServiceImpl menuServiceImpl;

    @PostMapping("")
    public ResponseEntity<?> createMenu(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam boolean isFreeShip,
                                              @RequestParam String timeShip,
                                              @RequestParam Double price,
                                              @RequestParam Integer cateId){
        ResponseData responseData= new ResponseData();
        boolean isSuccess =  menuServiceImpl.createMenu(
                file,
                title,
                isFreeShip,
                timeShip,
                price,
                cateId);
        responseData.setData(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
