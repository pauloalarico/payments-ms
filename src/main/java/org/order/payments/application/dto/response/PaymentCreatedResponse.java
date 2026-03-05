package org.order.payments.application.dto.response;

import org.order.payments.domain.enums.EventPayment;
import org.order.payments.domain.enums.MethodPayment;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public record PaymentCreatedResponse (
        EventPayment eventPayment,
        UUID paymentId,
        UUID correlationId,
        UUID customerId,
        BigDecimal amount,
        MethodPayment methodPayment,
        ZonedDateTime createdAt
) {
}
