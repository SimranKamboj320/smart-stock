package com.jsp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;

    private Double purchasePrice;
    private LocalDateTime purchaseDate;

    // Many purchases belong to one product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
