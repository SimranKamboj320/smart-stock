package com.jsp.service.impl;

import com.jsp.entity.AppUser;
import com.jsp.entity.OrderItem;
import com.jsp.entity.Product;
import com.jsp.exception.ResourceNotFoundException;
import com.jsp.exception.UserNotFoundException;
import com.jsp.repository.OrderItemRepository;
import com.jsp.repository.ProductRepository;
import com.jsp.repository.UserRepository;
import com.jsp.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemServiceImplementation implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= ADD ITEM TO CART for first time =================
    @Override
    public OrderItem addItemToCart(int productId, int quantity) {

        String email=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        AppUser user=userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User not exist."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        // check if cart already exists
        OrderItem existingItem =
                orderItemRepository
                        .findByUserIdAndProductIdAndOrderIsNull(user.getUserId(), productId);

        if (existingItem != null) {
            existingItem.setQuantity(
                    existingItem.getQuantity() + quantity
            );
            return existingItem;
        }

        OrderItem item = new OrderItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPriceAtPurchase(product.getPrice());
        item.setOrder(null); // important: cart item

        return orderItemRepository.save(item);
    }

    // ================= REMOVE ITEM FROM CART =================
    @Override
    public void removeItemFromCart(long orderItemId) {

        OrderItem item = orderItemRepository.findById(orderItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("OrderItem not found"));

        validateOwnership(item);

        orderItemRepository.delete(item);
    }

    // ================= UPDATE ITEM QUANTITY of existing item =================
    @Override
    public OrderItem updateItemQuantity(long orderItemId, int quantity) {

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        OrderItem item = orderItemRepository.findById(orderItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("OrderItem not found"));

        validateOwnership(item);

        item.setQuantity(quantity);
        return item;
    }

    // ================= VIEW CART ITEMS =================
    @Override
    public List<OrderItem> getOrderItemsForUser(int userId) {

        return orderItemRepository
                .findByUserIdAndOrderIsNull(userId);
    }

    // ================= CLEAR CART =================
    @Override
    public void clearOrderItemsForUser(int userId) {

        orderItemRepository
                .deleteByUserIdAndOrderIsNull(userId);
    }

    private void validateOwnership(OrderItem item) {
        String email=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        AppUser user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User does not exist."));

        if (item.getUser().getUserId() != user.getUserId()) {
            throw new RuntimeException("Unauthorized access");
        }
    }
}