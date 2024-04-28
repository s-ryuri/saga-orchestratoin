package com.example.application;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class CreateOrderCommand {

    @TargetAggregateIdentifier //유니크하다는 걸 명시해줌
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    @Builder
    public CreateOrderCommand(final String orderId,
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
