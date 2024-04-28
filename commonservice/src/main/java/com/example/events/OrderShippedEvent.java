package com.example.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderShippedEvent {
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    @Builder
    public OrderShippedEvent(final String shipmentId, final String orderId, final String shipmentStatus) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.shipmentStatus = shipmentStatus;
    }
}
