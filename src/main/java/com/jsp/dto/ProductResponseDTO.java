package com.jsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private String productName;
    private Double price;
    private String category;
    private String color;
    private String companyName;
    private LocalDate dateOfExpiry;
    private String description;
}
