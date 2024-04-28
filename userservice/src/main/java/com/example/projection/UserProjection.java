package com.example.projection;

import com.example.model.CardDetail;
import com.example.model.User;
import com.example.query.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        //Get The Deatils From the DB
        final CardDetail cardDetail = CardDetail.builder()
                                                .name("wee")
                                                .validUntilYear("2024")
                                                .validUntilMonth("12")
                                                .cardNumber("123214213123")
                                                .cvv(123)
                                                .build();

        return User.builder()
                   .userId(query.getUserId())
                   .firstName("seongryool")
                   .lastName("wee")
                   .cardDetail(cardDetail)
                   .build();
    }
}
