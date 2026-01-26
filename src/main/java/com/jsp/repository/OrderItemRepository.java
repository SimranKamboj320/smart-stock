package com.jsp.repository;

import com.jsp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

//    Optional<OrderItem> findByProductProductId(long productId);
//
//    List<OrderItem> findByUserId(int userId);
//
//    OrderItem findByUsersId(int userId);

    // cart items only
    List<OrderItem> findByUserIdAndOrderIsNull(int userId);

    // used while adding item
    OrderItem findByUserIdAndProductIdAndOrderIsNull(
            int userId, int productId
    );

    // clear cart
    void deleteByUserIdAndOrderIsNull(int userId);
}
