package com.jsp.controller;

import com.jsp.entity.OrderItem;
import com.jsp.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final OrderItemService orderItemService;

    @Autowired
    public CartController(OrderItemService orderItemService){
        this.orderItemService=orderItemService;
    }

    @GetMapping("/addToCart")
    public ResponseEntity<OrderItem> addItemToCart(@RequestParam int productItem, int quantity){
        OrderItem orderItem = orderItemService.addItemToCart(productItem, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItem);
    }

    @GetMapping("/getCart")
    public ResponseEntity<List<OrderItem>> getOrderItemsForUser(@RequestParam int userId){
        List<OrderItem> orderItem2 = orderItemService.getOrderItemsForUser(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(orderItem2);
    }

    @DeleteMapping("/deleteFromCart")
    public ResponseEntity<Void> deleteFromCart(@RequestParam long orderItemId){
            orderItemService.removeItemFromCart(orderItemId);
            return ResponseEntity.noContent().build();
    }
}
