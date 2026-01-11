package com.jsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String name;
    private long phoneNo;
    private String email;
    private String password;
    private LocalDate dob;
    private String gender;
    private String address;
    //private String role;
}
