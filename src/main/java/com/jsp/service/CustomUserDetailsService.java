package com.jsp.service;

import com.jsp.entity.AppUser;
import com.jsp.exception.UserNotFoundException;
import com.jsp.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email){
        // here our email treated as userName
        AppUser user= userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found with this email."));
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
