package com.jsp.service;

import com.jsp.dto.UserRequestDTO;
import com.jsp.dto.UserResponseDTO;
import com.jsp.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    //to save the user
    UserResponseDTO save(UserRequestDTO dto);

    Page<AppUser> findAllUser(Pageable pageable);

    //to search a user by id
    UserResponseDTO findByUserId(int userId);

    //to find phoneNumber of a user
    List<UserResponseDTO> findByPhoneNum(long phoneNumber);

    //to find user by email
    List<UserResponseDTO> findByUserEmail(String email);

    //to update user dob
    UserResponseDTO updateDOB(int userId, LocalDate dob);

    //to update user gender
    UserResponseDTO updateGender(int userId, String gender);

    //to update user address
    UserResponseDTO updateAddress(int userId, String address);

    //to update user
    UserResponseDTO update(int userId, UserRequestDTO dto);
}