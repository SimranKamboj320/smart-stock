package com.jsp.service;

import com.jsp.dto.UserRequestDTO;
import com.jsp.dto.UserResponseDTO;

import java.time.LocalDate;

public interface UserService {

    //to save the user
    UserResponseDTO save(UserRequestDTO dto);

    //to search a user by id
    UserResponseDTO findByUserId(int id);

    //to find phoneNumber of a user
    UserResponseDTO findByphoneNum(long phoneNumber);

    //to find user by email
    UserResponseDTO findByEmail(String email);

    //to update user dob
    UserResponseDTO updateDOB(int userId, LocalDate dob);

    //to update user gender
    UserResponseDTO updateGender(int userId, String gender);

    //to update user address
    UserResponseDTO updateAddress(int userId, String address);

    //
    UserResponseDTO update(int userId, UserRequestDTO dto);
}