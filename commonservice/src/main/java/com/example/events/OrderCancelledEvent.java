package com.example.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCancelledEvent {
    private String orderId;
    private String orderStatus;


    public OrderCancelledEvent(final String orderId, final String orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
