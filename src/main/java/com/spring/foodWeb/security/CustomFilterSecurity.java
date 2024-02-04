package com.spring.foodWeb.security;

import jakarta.servlet.annotation.WebFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Thông báo là lớp config sẽ thay đổi
@EnableWebSecurity // Thay đổi tầng security
public class CustomFilterSecurity {

    private UserDetailsService userDetailsService;

    @Autowired
    CustomJwtFilter customJwtFilter;

    public CustomFilterSecurity(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean // Ghi đè phương thức SecurityFilterChain của security
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.cors().disable() // tắt lỗi cors : chia sẻ tài nguyền
                .csrf().disable()// tắt lỗi tấn công bằng token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests() //cấu hình authorize ủy quyền cho api
                .requestMatchers("/login/**","/restaurant/file/**")
                .permitAll()// cho phép truy cập link trên
                .anyRequest()
                .authenticated();// cần auth để truy cập link khác


        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }






}
