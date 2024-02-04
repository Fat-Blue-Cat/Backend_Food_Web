package com.spring.foodWeb.service.impl;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImpl {
    boolean createMenu( MultipartFile file,
                        String title,
                        boolean isFreeShip,
                        String timeShip,
                        Double price,
                         Integer cateId);
}
