package com.spring.foodWeb.service;

import com.spring.foodWeb.dto.CategoryDTO;
import com.spring.foodWeb.dto.MenuDTO;
import com.spring.foodWeb.dto.RestaurantDTO;
import com.spring.foodWeb.entity.*;
import com.spring.foodWeb.repository.RestaurantRepository;
import com.spring.foodWeb.service.impl.FileServiceImpl;
import com.spring.foodWeb.service.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImpl {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImpl fileServiceImpl;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subTitle, String description, boolean isFreeShip, String address, String openDate) {
       boolean isInsertSuccess = false;
        try {
           boolean isSaveFileSuccess = fileServiceImpl.saveFile(file);

           if(isSaveFileSuccess){
               Restaurant restaurant = new Restaurant();
               restaurant.setTitle(title);
               restaurant.setSubtitle(subTitle);
               restaurant.setDescription(description);
               restaurant.setAddress(address);
               restaurant.setImage(file.getOriginalFilename());
               restaurant.setIsFreeShip(isFreeShip);

               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
               Date date = simpleDateFormat.parse(openDate);
               restaurant.setOpenDate(date);

               restaurantRepository.save(restaurant);
               isInsertSuccess = true;
           }

       }catch (Exception e){
           System.out.println("Error insert Restaurant");
       }

        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData =  restaurantRepository.findAll(pageRequest);

        for (Restaurant data:listData){
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setId(data.getId());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setFreeShip(data.getIsFreeShip());
            restaurantDTO.setSubTitle(data.getSubtitle());
            restaurantDTO.setRating(caculatorRating(data.getListRatingRestaurant()));

            restaurantDTOList.add(restaurantDTO);
        }
        return restaurantDTOList;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();

        if(restaurant.isPresent()){
            List<CategoryDTO> categoryDTOList = new ArrayList<>();

            Restaurant data = restaurant.get();
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setSubTitle(data.getSubtitle());
            restaurantDTO.setDesc(data.getDescription());
            restaurantDTO.setRating(caculatorRating(data.getListRatingRestaurant()));
            restaurantDTO.setFreeShip(data.getIsFreeShip());
            restaurantDTO.setOpenDate(data.getOpenDate());


            for(MenuRestaurant menuRestaurant: data.getMenuRestaurants()){
                List<MenuDTO> menuDTOList = new ArrayList<>();
                CategoryDTO categoryDTO = new CategoryDTO();

                categoryDTO.setName(menuRestaurant.getKey().getCategory().getNameCate());
                for (Food food: menuRestaurant.getKey().getCategory().getListFood()){
                    System.out.println(food.getTitle());

                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setImage(food.getImage());
                    menuDTO.setFreeShip(food.getIsFreeShip());
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setPrice(food.getPrice());
                    menuDTO.setDesc(food.getDescription());
                    menuDTO.setId(food.getId());
                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenuDTOList(menuDTOList);
                categoryDTOList.add(categoryDTO);

            }
            restaurantDTO.setCategoryDTOList(categoryDTOList);

        }

        return restaurantDTO;
    }

    public double caculatorRating(Set<RatingRestaurant> listRating){
        double totalPoint=0;
        for (RatingRestaurant data:listRating){
            totalPoint+= data.getRatePoint();
        }
        return totalPoint/listRating.size();
    }
}
