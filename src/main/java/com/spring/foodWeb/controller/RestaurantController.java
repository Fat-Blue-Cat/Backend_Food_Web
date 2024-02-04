package com.spring.foodWeb.controller;

import com.spring.foodWeb.response.ResponseData;
import com.spring.foodWeb.service.impl.FileServiceImpl;
import com.spring.foodWeb.service.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    FileServiceImpl fileService;

    @Autowired
    RestaurantServiceImpl  restaurantServiceImpl;

    @PostMapping("")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam String subTitle,
                                              @RequestParam String description,
                                              @RequestParam boolean isFreeShip,
                                              @RequestParam String address,
                                              @RequestParam String openDate){
        ResponseData responseData= new ResponseData();
        boolean isSuccess =  restaurantServiceImpl.insertRestaurant(
                 file,
                 title,
                 subTitle,
                 description,
                 isFreeShip,
                 address,
                 openDate);
        responseData.setData(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<?> getHomeRestaurant(){
        ResponseData responseData = new ResponseData();

        responseData.setData(restaurantServiceImpl.getHomePageRestaurant());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String fileName){
        Resource resource = fileService.loadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
