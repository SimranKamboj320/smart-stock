package com.jsp.service.impl;

import com.jsp.dto.OrderResponseDTO;
import com.jsp.entity.AppUser;
import com.jsp.entity.Order;
import com.jsp.entity.Product;
import com.jsp.entity.OrderItem;
import com.jsp.exception.ResourceNotFoundException;
import com.jsp.exception.UserNotFoundException;
import com.jsp.repository.OrderItemRepository;
import com.jsp.repository.OrderRepository;
import com.jsp.repository.UserRepository;
import com.jsp.service.OrderItemService;
import com.jsp.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;

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
    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository,OrderItemRepository orderItemRepository,OrderItemService orderItemService){
        this.userRepository = userRepository;
        this.orderRepository=orderRepository;
        this.orderItemRepository=orderItemRepository;
        this.orderItemService=orderItemService;
    }
    @Override
    @Transactional
    public OrderResponseDTO createOrder() {

        // 1. Get logged-in user
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        // 2. Fetch cart items
        List<OrderItem> items =
                orderItemService.getOrderItemsForUser(user.getId());

        if (items.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // 3. Create order
        Order order = new Order();
        order.setAppUser(user);
        order.setOrderDate(LocalDate.now());
        order.setStatus("PLACED");

        double totalAmount = 0;

        // 4. Process each cart item
        for (OrderItem item : items) {

            Product product = item.getProduct();

            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for " + product.getProductName()
                );
            }

            // reduce stock
            product.setStockQuantity(
                    product.getStockQuantity() - item.getQuantity()
            );
            productRepository.save(product);

            // attach order
            item.setOrder(order);

            totalAmount +=
                    item.getQuantity() * item.getPriceAtPurchase();
        }

        order.setTotalAmount(totalAmount);
        order.setOrderItems(items);

        // 5. Save order (cascade saves OrderItems)
        Order savedOrder = orderRepository.save(order);

        // 6. Clear cart
        orderItemService.clearOrderItemsForUser(user.getId());

        return entityToDTO(savedOrder);
    }


    @Override
    public OrderResponseDTO getOrderById(int orderId){
           Order order=orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Exist."));
           return entityToDTO(order);
    }

    public List<OrderResponseDTO> getAllMyOrder(Pageable pageable){
           String email=SecurityContextHolder.getContext()
                   .getAuthentication()
                   .getName();

           AppUser appUser=userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not found."));

           List<Order> orders=orderRepository.findByUserId(appUser.getUserId(),pageable);

           if(orders.isEmpty()){
               throw new ResourceNotFoundException("No Order Found");
           }

           return orders.stream()
                   .map(this::entityToDTO)
                   .toList();
    }

    @Override
    public List<OrderResponseDTO> getAllOrders(Pageable pageable){
           return orderRepository.findAll(pageable)
                   .stream()
                   .map(this::entityToDTO)
                   .collect(Collectors.toList());
    }


    @Override
    public OrderResponseDTO updateOrderStatus(int orderId,String status){
            Order order=orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not exist by this Id."));
            order.setStatus(status);
            orderRepository.save(order);
             return entityToDTO(order);
    }


}
