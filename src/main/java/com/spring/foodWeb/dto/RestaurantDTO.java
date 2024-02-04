package com.spring.foodWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
    private String image;
    private String title;
    private double rating;
    private String subTitle;
    private boolean isFreeShip;
}
