package com.example.query;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetUserPaymentDetailsQuery {
    private String userId;

    public GetUserPaymentDetailsQuery(final String userId) {
        this.userId = userId;
    }
}
