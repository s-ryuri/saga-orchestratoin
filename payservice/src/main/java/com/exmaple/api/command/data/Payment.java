package com.exmaple.api.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Payment {

    @Id
    private String paymentId;
    private String orderId;
    private Date timeStamp;
    private String paymentStatus;

    public Payment(final String paymentId, final String orderId, final Date timeStamp, final String paymentStatus) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.timeStamp = timeStamp;
        this.paymentStatus = paymentStatus;
    }

    public void changePaymentStatus(final String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
