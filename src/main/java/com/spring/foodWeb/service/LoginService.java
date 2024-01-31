package com.spring.foodWeb.service;

import com.spring.foodWeb.dto.UserDTO;
import com.spring.foodWeb.entity.Role;
import com.spring.foodWeb.entity.User;
import com.spring.foodWeb.repository.UserRepository;
import com.spring.foodWeb.request.SignupRequest;
import com.spring.foodWeb.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImpl {
    @Autowired
    UserRepository userRepository;


    public List<UserDTO> getAllUser() {
        List<User> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(User user: listUser){
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
        List<User> listUser = userRepository.findByUserNameAndPassword(userName,password);

        return listUser.size()>0;
    }

    @Override
    public Boolean addUser(SignupRequest signupRequest) {
        Role role = new Role();
        User user = new User();
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
