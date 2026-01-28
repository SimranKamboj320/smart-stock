package com.jsp.controller;

import com.jsp.dto.OrderResponseDTO;

import com.jsp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderResponseDTO>  createOrder(){
        OrderResponseDTO orderResponse = orderService.createOrder();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
