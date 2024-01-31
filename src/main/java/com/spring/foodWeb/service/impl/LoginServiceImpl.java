package com.spring.foodWeb.service.impl;

import com.spring.foodWeb.dto.UserDTO;
import com.spring.foodWeb.request.SignupRequest;

import java.util.List;

public interface LoginServiceImpl {
    List<UserDTO>  getAllUser();
    Boolean checkLogin(String userName, String password);
    Boolean addUser(SignupRequest signupRequest);
}
