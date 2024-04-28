package com.example.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCompletedEvent {
    private String orderId;
    private String orderStatus;

    @Builder
    public OrderCompletedEvent(final String orderId, final String orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
