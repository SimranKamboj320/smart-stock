package com.jsp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemId;

    private int quantity;

// price of ONE unit at the time of adding to cart
    private double priceAtPurchase;

    // Many cart items belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // Many order items refer to one product
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    // Null = cart item, Not null = placed order
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
