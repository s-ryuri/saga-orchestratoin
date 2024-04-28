package com.example.command.api.events;

import com.example.command.api.data.Shipment;
import com.example.command.api.data.ShipmentJpaRepository;
import com.example.events.OrderShippedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipmentsEventHandler {

    private final ShipmentJpaRepository shipmentJpaRepository;

    @EventHandler
    public void on(OrderShippedEvent event) {
        final Shipment shipment = Shipment.builder()
                                          .shipmentId(event.getShipmentId())
                                          .orderId(event.getOrderId())
                                          .shipmentStatus(event.getShipmentStatus())
                                          .build();
        shipmentJpaRepository.save(shipment);
    }
}
