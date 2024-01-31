package com.spring.foodWeb.controller;

import com.spring.foodWeb.entity.Role;
import com.spring.foodWeb.request.SignupRequest;
import com.spring.foodWeb.response.ResponseData;
import com.spring.foodWeb.service.LoginService;
import com.spring.foodWeb.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImpl loginServiceImpl;
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String userName, @RequestParam String password){
        ResponseData responseData = new ResponseData();
        if(loginServiceImpl.checkLogin(userName,password)){
            responseData.setData(true);
        }else {
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImpl.addUser(signupRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
