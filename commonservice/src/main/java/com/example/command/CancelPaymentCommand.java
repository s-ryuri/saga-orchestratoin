package com.example.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor
public class CancelPaymentCommand {
    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus = "CANCELLED";

    public CancelPaymentCommand(final String paymentId, final String orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }
}
