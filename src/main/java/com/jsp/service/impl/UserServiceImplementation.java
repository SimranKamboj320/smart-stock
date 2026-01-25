package com.jsp.service.impl;

import com.jsp.dto.UserRequestDTO;
import com.jsp.dto.UserResponseDTO;
import com.jsp.entity.AppUser;
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
    public AppUser mapToEntity(UserRequestDTO userDto){
        AppUser u = new AppUser();
        u.setName(userDto.getName());
        u.setPhoneNo(userDto.getPhoneNo());
        u.setEmail(userDto.getEmail());
        u.setDob(userDto.getDob());
        u.setGender(userDto.getGender());
        u.setAddress(userDto.getAddress());
        return u;
    }

    //map entity to dto
    public UserResponseDTO mapToDto(AppUser u){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setName(u.getName());
        dto.setPhoneNo(u.getPhoneNo());
        dto.setEmail(u.getEmail());
        dto.setDob(u.getDob());
        dto.setGender(u.getGender());
        dto.setAddress(u.getAddress());
        return dto;
    }

    public Page<UserResponseDTO> mapToDto(Page<AppUser> users){
        return users.map(this::mapToDto);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO dto){
        AppUser appUser = mapToEntity(dto);
        return mapToDto(userRepository.save(appUser));
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
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        appUser.setDob(dob);

        return mapToDto(userRepository.save(appUser));
    }

    public UserResponseDTO updateGender(int userId, String gender){
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        appUser.setGender(gender);

        return mapToDto(userRepository.save(appUser));
    }

    public UserResponseDTO updateAddress(int userId, String address){
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        appUser.setAddress(address);

        return mapToDto(userRepository.save(appUser));
    }

    @Override
    @Transactional
    public UserResponseDTO update(int userId, UserRequestDTO dto){
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User does not exist."));
        appUser.setName(dto.getName());
        appUser.setPhoneNo(dto.getPhoneNo());
        appUser.setEmail(dto.getEmail());
        appUser.setDob(dto.getDob());
        appUser.setGender(dto.getGender());
        appUser.setAddress(dto.getAddress());

        return mapToDto(userRepository.save(appUser));
    }
}