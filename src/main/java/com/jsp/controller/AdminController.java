package com.jsp.controller;

import com.jsp.dto.OrderResponseDTO;
import com.jsp.dto.ProductResponseDTO;
import com.jsp.entity.AppUser;
import com.jsp.service.OrderService;
import com.jsp.service.ProductService;
import com.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderResponseDTO>> getAllMyOrder(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size){

        return ResponseEntity.ok(orderService.getAllMyOrder(PageRequest.of(page, size)));
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<Page<ProductResponseDTO>> getAllProduct(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size){

        return ResponseEntity.ok(productService.findAllProduct(PageRequest.of(page, size)));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Page<AppUser>> findAllUser(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size){

        return ResponseEntity.ok(userService.findAllUser(PageRequest.of(page, size)));
    }

    @GetMapping("/updateStatus")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@RequestParam int orderId, String status){
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}
