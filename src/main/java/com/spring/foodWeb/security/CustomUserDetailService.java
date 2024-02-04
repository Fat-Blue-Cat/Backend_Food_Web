package com.spring.foodWeb.security;

import com.spring.foodWeb.entity.Users;
import com.spring.foodWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = userRepository.findByUserName(userName);
        if(users == null){
            throw new UsernameNotFoundException("username's not exist" + userName);
        }
        return new org.springframework.security.core.userdetails.User(userName, users.getPassword(),new ArrayList<>());
    }
}
