package com.example.events;

import com.example.data.Order;
import com.example.data.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventsHandler {

    private final OrderJpaRepository orderJpaRepository;

    @EventHandler
    public void on(OrderCreateEvent event) {
        final Order order = Order.builder()
                                 .orderId(event.getOrderId())
                                 .orderStatus(event.getOrderStatus())
                                 .userId(event.getUserId())
                                 .quantity(event.getQuantity())
                                 .addressId(event.getAddressId())
                                 .productId(event.getProductId())
                                 .build();
        orderJpaRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        final Order order = orderJpaRepository.findById(event.getOrderId()).get();
        order.changeOrderStatus(event.getOrderStatus());
        orderJpaRepository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        final Order order = orderJpaRepository.findById(event.getOrderId()).get();
        order.changeOrderStatus(event.getOrderStatus());
        orderJpaRepository.save(order);
    }
}
