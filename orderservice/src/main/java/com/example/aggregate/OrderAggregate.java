package com.example.aggregate;

import com.example.application.CreateOrderCommand;
import com.example.command.CancelOrderCommand;
import com.example.command.CompleteOrderCommand;
import com.example.events.OrderCancelledEvent;
import com.example.events.OrderCompletedEvent;
import com.example.events.OrderCreateEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        OrderCreateEvent orderCreateEvent = OrderCreateEvent.builder()
                                                            .orderId(command.getOrderId())
                                                            .addressId(command.getAddressId())
                                                            .userId(command.getUserId())
                                                            .quantity(command.getQuantity())
                                                            .productId(command.getProductId())
                                                            .orderStatus(command.getOrderStatus())
                                                            .build();
        AggregateLifecycle.apply(orderCreateEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreateEvent event) {
        this.orderId = event.getOrderId();
        this.productId = event.getProductId();
        this.userId = event.getUserId();
        this.addressId = event.getAddressId();
        this.quantity = event.getQuantity();
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
        //validate the command
        //publish Order Completed Event
        final OrderCompletedEvent orderCompletedEvent = OrderCompletedEvent.builder()
                                                                           .orderId(command.getOrderId())
                                                                           .orderStatus(command.getOrderStatus())
                                                                           .build();

        AggregateLifecycle.apply(orderCompletedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(CancelOrderCommand command) {
        final OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent(command.getOrderId(), command.getOrderStatus());
        AggregateLifecycle.apply(orderCancelledEvent);
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        this.orderStatus = event.getOrderStatus();
    }
}
