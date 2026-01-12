package com.jsp.controller;

import com.jsp.dto.ProductRequestDTO;
import com.jsp.dto.ProductResponseDTO;
import com.jsp.dto.UserRequestDTO;
import com.jsp.dto.UserResponseDTO;
import com.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(userService.save(dto));
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<UserResponseDTO> findByUserId(@RequestParam int userId){
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @GetMapping("/findByPhoneNum")
    public ResponseEntity<List<UserResponseDTO>> findByPhoneNum(@RequestParam long phoneNumber){
        return ResponseEntity.ok(userService.findByPhoneNum(phoneNumber));
    }

    @GetMapping("/findByUserEmail")
    public ResponseEntity<List<UserResponseDTO>> findByUserEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.findByUserEmail(email));
    }

    @PutMapping("/updateDob")
    public ResponseEntity<UserResponseDTO> updateDOB(@RequestParam int userId,@RequestBody LocalDate dob){
        return ResponseEntity.ok(userService.updateDOB(userId, dob));
    }

    @PutMapping("/updateGender")
    public ResponseEntity<UserResponseDTO> updateGender(@RequestParam int userId, @RequestBody String gender){
        return ResponseEntity.ok(userService.updateGender(userId, gender));
    }

    @PutMapping("/updateAddress")
    public ResponseEntity<UserResponseDTO> updateAddress(@RequestParam int userId, @RequestBody String address){
        return ResponseEntity.ok(userService.updateAddress(userId, address));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> updateProduct(@RequestParam int userId, @RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(userService.update(userId,dto));
    }
}