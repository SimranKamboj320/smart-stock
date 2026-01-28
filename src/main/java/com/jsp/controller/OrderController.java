package com.jsp.controller;

import com.jsp.dto.OrderResponseDTO;
import com.jsp.dto.ProductResponseDTO;
import com.jsp.repository.UserRepository;
import com.jsp.service.OrderService;
import com.jsp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;

public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<OrderResponseDTO>  createOrder(){
        OrderResponseDTO orderResponse = orderService.createOrder();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
