package com.example.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor
public class CompleteOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private String orderStatus;

    @Builder
    public CompleteOrderCommand(final String orderId, final String orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
