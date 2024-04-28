package com.exmaple.api.command.events;

import com.example.events.PaymentCancelledEvent;
import com.example.events.PaymentProcessedEvent;
import com.exmaple.api.command.data.Payment;
import com.exmaple.api.command.data.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PaymentsEventHandler {

    private final PaymentJpaRepository paymentJpaRepository;

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        final Payment payment = new Payment(event.getPaymentId(), event.getOrderId(), new Date(), "COMPLETED");
        paymentJpaRepository.save(payment);
    }

    @EventHandler
    public void on(PaymentCancelledEvent event) {
        final Payment payment = paymentJpaRepository.findById(event.getPaymentId()).get();
        payment.changePaymentStatus(event.getPaymentStatus());
        paymentJpaRepository.save(payment);
    }
}
