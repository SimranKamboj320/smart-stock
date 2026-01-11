package com.jsp.service.impl;

import com.jsp.dto.ProductRequestDTO;
import com.jsp.dto.ProductResponseDTO;
import com.jsp.dto.UserRequestDTO;
import com.jsp.dto.UserResponseDTO;
import com.jsp.entity.Product;
import com.jsp.entity.User;
import com.jsp.exception.ProductNotFoundException;
import com.jsp.exception.UserNotFoundException;
import com.jsp.repository.UserRepository;
import com.jsp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;

    //map dto to entity
    public User mapToEntity(UserRequestDTO userDto){
        User u = new User();
        u.setName(userDto.getName());
        u.setPhoneNo(userDto.getPhoneNo());
        u.setEmail(userDto.getEmail());
        u.setDob(userDto.getDob());
        u.setGender(userDto.getGender());
        u.setAddress(userDto.getAddress());
        return u;
    }

    //map entity to dto
    public UserResponseDTO mapToDto(User u){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setName(u.getName());
        dto.setPhoneNo(u.getPhoneNo());
        dto.setEmail(u.getEmail());
        dto.setDob(u.getDob());
        dto.setGender(u.getGender());
        dto.setAddress(u.getAddress());
        return dto;
    }

    public Page<UserResponseDTO> mapToDto(Page<User> users){
        return users.map(this::mapToDto);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO dto){
        User user = mapToEntity(dto);
        return mapToDto(userRepository.save(user));
    }

    @Override
    public UserResponseDTO findByUserId(int userId){
        return mapToDto(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with this id")));
    }

    @Override
    public List<UserResponseDTO> findByPhoneNum(long phoneNumber){
        return userRepository.findByPhoneNum(phoneNumber)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<UserResponseDTO> findByUserEmail(String email){
        return userRepository.findByEmail(email)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserResponseDTO updateDOB(int userId, LocalDate dob){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        user.setDob(dob);

        return mapToDto(userRepository.save(user));
    }

    public UserResponseDTO updateGender(int userId, String gender){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        user.setGender(gender);

        return mapToDto(userRepository.save(user));
    }

    public UserResponseDTO updateAddress(int userId, String address){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        user.setAddress(address);

        return mapToDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponseDTO update(int userId, UserRequestDTO dto){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist."));
        user.setName(dto.getName());
        user.setPhoneNo(dto.getPhoneNo());
        user.setEmail(dto.getEmail());
        user.setDob(dto.getDob());
        user.setGender(dto.getGender());
        user.setAddress(dto.getAddress());

        return mapToDto(userRepository.save(user));
    }
}