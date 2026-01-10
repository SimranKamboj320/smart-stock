package com.jsp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    @Id
    private int userId;
    private String name;
    private long phoneNo;
    private String email;
    private LocalDate dob;
    private String gender;
    private String address;
    private String password;
//    private String role;
}
