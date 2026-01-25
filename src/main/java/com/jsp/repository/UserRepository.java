package com.jsp.repository;

import com.jsp.dto.UserResponseDTO;
import com.jsp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    public UserResponseDTO findByUserId(int userId);

    List<AppUser> findByPhoneNum(long phoneNum);

    List<AppUser> findByEmail(String email);

}