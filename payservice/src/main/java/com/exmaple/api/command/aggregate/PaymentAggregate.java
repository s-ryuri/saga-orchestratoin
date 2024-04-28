package com.exmaple.api.command.aggregate;

import com.example.command.CancelPaymentCommand;
import com.example.command.ValidatePaymentCommand;
import com.example.events.PaymentCancelledEvent;
import com.example.events.PaymentProcessedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
@NoArgsConstructor
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        // Validate the Payment Details
        // publish the payment processed event
        log.info("Executing ValidatePaymentCommand for OrderId {} PaymentId : {}",
                 validatePaymentCommand.getOrderId(),
                 validatePaymentCommand.getPaymentId());

        final PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent(validatePaymentCommand.getPaymentId(),
                                                                                      validatePaymentCommand.getOrderId());

        AggregateLifecycle.apply(paymentProcessedEvent);

        log.info("페이먼트 이벤트 어플라이 완료");
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event) {
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }

    @CommandHandler
    public void handle(CancelPaymentCommand command) {
        final PaymentCancelledEvent paymentCancelledEvent = new PaymentCancelledEvent(command.getPaymentId(),
                                                                                      command.getOrderId(),
                                                                                      command.getPaymentStatus());
        AggregateLifecycle.apply(paymentCancelledEvent);
    }

    @EventSourcingHandler
    public void on(PaymentCancelledEvent event) {
        this.paymentStatus = event.getPaymentStatus();
    }

}
