package com.spring.foodWeb.repository;

import com.spring.foodWeb.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    List<Users> findByUserNameAndPassword(String userName, String password);
    Users findByUserName(String userName);
}
