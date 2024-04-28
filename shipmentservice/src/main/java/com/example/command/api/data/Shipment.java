package com.example.command.api.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Shipment {

    @Id
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    @Builder
    public Shipment(final String shipmentId, final String orderId, final String shipmentStatus) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.shipmentStatus = shipmentStatus;
    }
}
