package com.example.command.api.aggregate;

import com.example.command.ShipOrderCommand;
import com.example.events.OrderShippedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class ShipmentAggregate {
    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand command) {
        //validate the command
        //publish the order shipped event
        final OrderShippedEvent event = OrderShippedEvent.builder()
                                                         .shipmentId(command.getShipmentId())
                                                         .orderId(command.getOrderId())
                                                         .shipmentStatus("COMPLETED")
                                                         .build();

        AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void on(OrderShippedEvent event) {
        this.orderId = event.getOrderId();
        this.shipmentId = event.getShipmentId();
        this.shipmentStatus = event.getShipmentStatus();

    }
}
