package com.jsp.repository;

import com.jsp.dto.UserResponseDTO;
import com.jsp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    public UserResponseDTO findByUserId(int userId);

    List<AppUser> findByPhoneNum(long phoneNum);

    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}