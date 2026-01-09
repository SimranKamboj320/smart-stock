package com.jsp.dto;

import java.time.LocalDateTime;

public class PurchaseResponseDTO {
    private int purchaseId;
    private int quantity;
    private Double PurchasePrice;
    private LocalDateTime purchaseDate;
    private String message;

    // Product info
    private int productId;
    private String productName;

}
