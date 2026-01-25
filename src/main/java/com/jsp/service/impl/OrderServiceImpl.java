package com.jsp.service.impl;

import com.jsp.dto.OrderResponseDTO;
import com.jsp.entity.Order;
import com.jsp.exception.ResourceNotFoundException;
import com.jsp.repository.OrderRepository;
import com.jsp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    // mapper for entity to dto
    private OrderResponseDTO entityToDTO(Order order){
        OrderResponseDTO dto=new OrderResponseDTO();
        //dto.setProductCount(order.ge);
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setType(order.getType());
        // need some extra thing
        return dto;
    }

    private final OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public OrderResponseDTO getOrderById(int orderId){
           Order order=orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Exist."));
           return entityToDTO(order);
    }

//    public List<OrderResponseDTO> getAllMyOrder(Pageable pageable){
//
//
//    }
    @Override
    public OrderResponseDTO updateOrderStatus(int orderId,String status){
            Order order=orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not exist by this Id."));
            order.setStatus(status);
            orderRepository.save(order);
             return entityToDTO(order);
    }
}
