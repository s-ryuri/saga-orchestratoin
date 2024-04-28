package com.example.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentProcessedEvent {
    private String paymentId;
    private String orderId;

    public PaymentProcessedEvent(final String paymentId, final String orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
