package com.jsp.service;

import com.jsp.dto.OrderResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    // to place order
    //OrderResponseDTO createOrder();

    // for admin
    OrderResponseDTO getOrderById(int orderId);

    // When user login then user can see their all order
    //List<OrderResponseDTO> getAllMyOrder(Pageable pageable);

    // admin can see all order
    //List<OrderResponseDTO> getAllOrders(Pageable pageable);

    // for admin to update order status
    OrderResponseDTO updateOrderStatus(int orderId,String status);
}
