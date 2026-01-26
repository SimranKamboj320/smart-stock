package com.jsp.service.impl;

import com.jsp.entity.OrderItem;
import com.jsp.exception.OrderNotFoundException;
import com.jsp.exception.UserNotFoundException;
import com.jsp.repository.OrderItemRepository;
import com.jsp.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem addItemToCart(int productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        OrderItem existingItem = orderItemRepository.findByProductProductId(productId)
                .orElse(null);

        if (existingItem != null) {
            // ✅ already in cart → increase quantity
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return orderItemRepository.save(existingItem);
        }
        OrderItem newItem = new OrderItem();
        newItem.setQuantity(quantity);

        return orderItemRepository.save(newItem);
    }

    @Override
    public void removeItemFromCart(long orderItemId){
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderItemRepository.delete(orderItem);
    }

    @Override
    public OrderItem updateItemQuantity(long orderItemId, int quantity){

        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderItem.setQuantity(quantity);

        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemsUser(int userId){
        List<OrderItem> items = orderItemRepository.findByUserId(userId);
        items.stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("Cart is empty for userId: " + userId));

        return items;
    }

    @Override
    public void clearOrderItemsForUser(int userId){
        OrderItem orderItem = orderItemRepository.findByUsersId(userId);
        orderItemRepository.delete(orderItem);
    }
}