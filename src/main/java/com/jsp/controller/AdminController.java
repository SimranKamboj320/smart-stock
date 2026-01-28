package com.jsp.controller;

import com.jsp.dto.OrderResponseDTO;
import com.jsp.dto.ProductResponseDTO;
import com.jsp.dto.UserResponseDTO;
import com.jsp.service.OrderService;
import com.jsp.service.ProductService;
import com.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;

    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public AdminController(OrderService orderService, ProductService productService, UserService userService){
        this.orderService=orderService;
        this.productService=productService;
        this.userService=userService;
    }

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
    public ResponseEntity<Page<UserResponseDTO>> findAllUser(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){

        return ResponseEntity.ok(userService.findAllUser(PageRequest.of(page, size)));
    }

    @GetMapping("/updateStatus")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@RequestParam int orderId, String status){
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}
