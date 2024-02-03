package com.spring.foodWeb.service;

import com.spring.foodWeb.dto.UserDTO;
import com.spring.foodWeb.entity.Roles;
import com.spring.foodWeb.entity.Users;
import com.spring.foodWeb.repository.UserRepository;
import com.spring.foodWeb.request.SignupRequest;
import com.spring.foodWeb.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public List<UserDTO> getAllUser() {
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(Users user: listUser){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(user.getUserName());
            userDTO.setId(user.getId());
            userDTO.setFullName(user.getFullName());
            userDTO.setPassword(user.getPassword());

            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    public Boolean checkLogin(String userName, String password) {
        Users user = userRepository.findByUserName(userName);


        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public Boolean addUser(SignupRequest signupRequest) {
        Roles role = new Roles();
        Users user = new Users();
        role.setId(signupRequest.getRoleId());
        user.setUserName(signupRequest.getEmail());
        user.setFullName(signupRequest.getFullName());
        user.setPassword(signupRequest.getPassword());
        user.setRole(role);
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
