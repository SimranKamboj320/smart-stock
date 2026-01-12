package com.jsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private int orderId;
    private int productCount;
    private LocalDate orderDate;
    private Double totalAmount;
    private String status;
    private String type;
    // User info
    private int userId;
    private String userName;

    // Order items summary

}
