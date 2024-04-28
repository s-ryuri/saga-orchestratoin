package com.example.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateEvent {
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    @Builder
    public OrderCreateEvent(final String orderId,
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
}
