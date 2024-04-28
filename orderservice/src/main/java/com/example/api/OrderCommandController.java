package com.example.api;

import com.example.application.CreateOrderCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    private final CommandGateway commandGateway;

    //30:12 그림 확인
    @PostMapping("")
    public String createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        String orderId = UUID.randomUUID().toString();

        final CreateOrderCommand orderCommand = CreateOrderCommand.builder()
                                                                  .orderId(orderId)
                                                                  .addressId(orderCreateDto.getAddressId())
                                                                  .productId(orderCreateDto.getProductId())
                                                                  .quantity(orderCreateDto.getQuantity())
                                                                  .userId(orderCreateDto.getUserId())
                                                                  .orderStatus("CREATED")
                                                                  .build();

        commandGateway.sendAndWait(orderCommand);

        return "Order created";
    }
}
