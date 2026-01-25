package com.jsp.service.impl;

import com.jsp.entity.OrderItem;
import com.jsp.entity.Product;
import com.jsp.exception.ProductNotFoundException;
import com.jsp.exception.UserNotFoundException;
import com.jsp.repository.OrderItemRepository;
import com.jsp.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    OrderItem findByOrderId(long orderId){
        return orderItemRepository.findById(orderId).orElseThrow(() -> new UserNotFoundException("Order not found"));
    }

}
