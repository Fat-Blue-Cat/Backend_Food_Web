package com.spring.foodWeb.service;

import com.spring.foodWeb.entity.Category;
import com.spring.foodWeb.entity.Food;
import com.spring.foodWeb.entity.Restaurant;
import com.spring.foodWeb.repository.FoodRepository;
import com.spring.foodWeb.service.impl.FileServiceImpl;
import com.spring.foodWeb.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements MenuServiceImpl {
    @Autowired
    FileServiceImpl fileServiceImpl;

    @Autowired
    FoodRepository foodRepository;
    @Override
    public boolean createMenu(MultipartFile file, String title, boolean isFreeShip, String timeShip, Double price, Integer cateId) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImpl.saveFile(file);

            if(isSaveFileSuccess){
                Food food = new Food();
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(timeShip);
                food.setPrice(price);
                food.setIsFreeShip(isFreeShip);

                Category category = new Category();
                category.setId(cateId);
                food.setCategory(category);

                foodRepository.save(food);
                isInsertSuccess = true;
            }

        }catch (Exception e){
            System.out.println("Error insert Restaurant");
        }

        return isInsertSuccess;
    }
}
