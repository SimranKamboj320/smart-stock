package com.jsp.service;

import com.jsp.entity.OrderItem;


import java.util.List;
public interface OrderItemService {

    //Add product to cart (or increase quantity if already exists)
    OrderItem addItemToCart(int productItem, int quantity);

    //Remove a single item from cart
    void removeItemFromCart(long orderItemId);

    //Update quantity of an existing cart item
    OrderItem updateItemQuantity(long orderItemId, int quantity);

    //view all cart items of logged-in user
    List<OrderItem> getOrderItemsForUser(int userId);

    //Clear cart after order is placed
    void clearOrderItemsForUser(int userId);
}