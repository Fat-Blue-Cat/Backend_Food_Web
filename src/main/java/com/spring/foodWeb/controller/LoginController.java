package com.spring.foodWeb.controller;

import com.spring.foodWeb.request.SignupRequest;
import com.spring.foodWeb.response.ResponseData;
import com.spring.foodWeb.service.impl.LoginServiceImpl;
import com.spring.foodWeb.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImpl loginServiceImpl;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String userName, @RequestParam String password){
        ResponseData responseData = new ResponseData();
        if(loginServiceImpl.checkLogin(userName,password)){
            String jwt = jwtUtilsHelper.generateToken(userName);
            responseData.setData(jwt);
        }else {
            responseData.setSuccess(false);
            responseData.setData("");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImpl.addUser(signupRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    public ResponseEntity<?> getAllUser(@RequestBody SignupRequest signupRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImpl.addUser(signupRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
