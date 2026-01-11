package com.jsp.repository;

import com.jsp.dto.UserResponseDTO;
import com.jsp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public UserResponseDTO findByUserId(int userId);

    List<User> findByPhoneNum(long phoneNum);

    List<User> findByEmail(String email);

}