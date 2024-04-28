package com.example.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentCancelledEvent {
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    public PaymentCancelledEvent(final String paymentId, final String orderId, final String paymentStatus) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
    }
}
