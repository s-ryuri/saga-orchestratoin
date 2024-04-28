package com.example.api;

import com.example.model.User;
import com.example.query.GetUserPaymentDetailsQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final QueryGateway queryGateway;

    @GetMapping("/{userId}")
    public User getUserPaymentDetails(@PathVariable String userId) {
        final GetUserPaymentDetailsQuery getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery(userId);

        return queryGateway.query(getUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class))
                           .join();
    }
}
