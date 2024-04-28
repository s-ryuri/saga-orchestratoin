package com.example.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private String orderStatus = "CANCELED";

    public CancelOrderCommand(final String orderId) {
        this.orderId = orderId;
    }
}
