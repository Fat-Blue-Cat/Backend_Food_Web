package com.spring.foodWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
    private int id;
    private String image;
    private String title;
    private double rating;
    private String subTitle;
    private boolean isFreeShip;

    private Date openDate;

    private String desc;

    private List<CategoryDTO> categoryDTOList;
}
