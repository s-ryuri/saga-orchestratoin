package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardDetail {
    private String name;
    private String cardNumber;
    private String validUntilMonth;
    private String validUntilYear;
    private Integer cvv;

    @Builder
    public CardDetail(final String name,
                      final String cardNumber,
                      final String validUntilMonth,
                      final String validUntilYear,
                      final Integer cvv) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.validUntilMonth = validUntilMonth;
        this.validUntilYear = validUntilYear;
        this.cvv = cvv;
    }
}
