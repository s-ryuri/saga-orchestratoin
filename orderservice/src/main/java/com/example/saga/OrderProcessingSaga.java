package com.example.saga;

import com.example.command.CancelOrderCommand;
import com.example.command.CancelPaymentCommand;
import com.example.command.CompleteOrderCommand;
import com.example.command.ShipOrderCommand;
import com.example.command.ValidatePaymentCommand;
import com.example.events.OrderCancelledEvent;
import com.example.events.OrderCompletedEvent;
import com.example.events.OrderCreateEvent;
import com.example.events.OrderShippedEvent;
import com.example.events.PaymentCancelledEvent;
import com.example.events.PaymentProcessedEvent;
import com.example.model.User;
import com.example.query.GetUserPaymentDetailsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

//여기서 시작해서 여기서 끝남

@Saga
@Slf4j
public class OrderProcessingSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga //사가 시작
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreateEvent event) {
        log.info("OrdeCreateEvent in Saga Order Id : {}", event.getOrderId());

        final GetUserPaymentDetailsQuery getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery(event.getUserId());

        User user = null;

        try {
            user = queryGateway.query(getUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class))
                               .join();
        } catch (Exception e) {
            log.error(e.getMessage());
            //보상 트랜잭션
            cancelOrderCommand(event.getOrderId());
        }

        final ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
                                                                                    .cardDetail(user.getCardDetail())
                                                                                    .orderId(event.getOrderId())
                                                                                    .paymentId(UUID.randomUUID().toString())
                                                                                    .build();

        commandGateway.sendAndWait(validatePaymentCommand);
    }

    private void cancelOrderCommand(final String orderId) {
        final CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(orderId);
        commandGateway.send(cancelOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event) {
        log.info("PaymentProcessedEvent in Saga Order Id : {}", event.getOrderId());
        try {
            if (true) {
                throw new Exception();
            }
            final ShipOrderCommand command = ShipOrderCommand.builder()
                                                             .shipmentId(UUID.randomUUID().toString())
                                                             .orderId(event.getOrderId())
                                                             .build();
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 보상트랜잭션
            cancelPaymentCommand(event);
        }
    }

    private void cancelPaymentCommand(final PaymentProcessedEvent event) {
        final CancelPaymentCommand cancelPaymentCommand = new CancelPaymentCommand(event.getPaymentId(), event.getOrderId());
        commandGateway.send(cancelPaymentCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderShippedEvent event) {
        log.info("OrderShippedEvent in Saga Order Id : {}", event.getOrderId());
        try {
            final CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                                                                                  .orderId(event.getOrderId())
                                                                                  .orderStatus("APPROVED")
                                                                                  .build();
            commandGateway.send(completeOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in Saga Order Id : {}", event.getOrderId());
        //Do Nothing
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in Saga Order Id : {}", event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentCancelledEvent event) {
        log.info("PaymentCancelledEvent in Saga Order Id : {}", event.getOrderId());
        cancelOrderCommand(event.getOrderId());
    }

}
