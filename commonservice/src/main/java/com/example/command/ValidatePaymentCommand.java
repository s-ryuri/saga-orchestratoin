package com.example.command;

import com.example.model.CardDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Getter
@NoArgsConstructor
public class ValidatePaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private CardDetail cardDetail;

    @Builder
    public ValidatePaymentCommand(final String paymentId, final String orderId, final CardDetail cardDetail) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.cardDetail = cardDetail;
    }
}
