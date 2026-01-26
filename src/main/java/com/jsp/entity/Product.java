package com.jsp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;
    private Double price;
    private int stockQuantity;
    private String category;
    private String color;
    private String companyName;
    private LocalDate dateOfExpiry;
    private String description;

    // One product can have many purchases
//    @OneToMany(mappedBy = "product")
//    private List<Purchase> purchases;
}
