package com.example.api;

import lombok.Getter;

@Getter
public class OrderCreateDto {
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
}
