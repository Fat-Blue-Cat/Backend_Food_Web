package com.spring.foodWeb.service.impl;

import com.spring.foodWeb.dto.RestaurantDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImpl {
    boolean insertRestaurant( MultipartFile file,
                                String title,
                                String subTitle,
                                String description,
                                 boolean isFreeShip,
                                String address,
                                String openDate);
    List<RestaurantDTO> getHomePageRestaurant();

    RestaurantDTO getDetailRestaurant(int id);

}
