package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private CardDetail cardDetail;

    @Builder
    public User(final String userId, final String firstName, final String lastName, final CardDetail cardDetail) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardDetail = cardDetail;
    }
}
