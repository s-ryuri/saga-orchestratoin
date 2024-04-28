package com.example.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor
public class ShipOrderCommand {
    @TargetAggregateIdentifier
    private String shipmentId;
    private String orderId;

    @Builder
    public ShipOrderCommand(final String shipmentId, final String orderId) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
    }
}
