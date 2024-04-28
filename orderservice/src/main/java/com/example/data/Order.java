package com.example.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    @Builder
    public Order(final String orderId,
                 final String productId,
                 final String userId,
                 final String addressId,
                 final Integer quantity,
                 final String orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }

    public void changeOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
