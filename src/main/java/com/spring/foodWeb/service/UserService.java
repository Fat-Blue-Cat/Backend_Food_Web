package com.spring.foodWeb.service;

import com.spring.foodWeb.dto.UserDTO;
import com.spring.foodWeb.entity.Users;
import com.spring.foodWeb.repository.UserRepository;
import com.spring.foodWeb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Override
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
}
