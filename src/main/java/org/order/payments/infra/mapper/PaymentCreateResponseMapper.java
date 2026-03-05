package org.order.payments.infra.mapper;

import org.order.payments.application.dto.response.PaymentCreatedResponse;
import org.order.payments.domain.enums.EventPayment;
import org.order.payments.domain.enums.MethodPayment;
import org.order.payments.domain.model.Payment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class PaymentCreateResponseMapper {
    public PaymentCreatedResponse toDto(Payment payment) {
        EventPayment eventPayment = payment.getEvent();
        UUID paymentId = payment.getPaymentId();
        UUID correlationId = payment.getCorrelationId();
        UUID customerId = payment.getCustomer();
        BigDecimal amount = payment.getAmount();
        MethodPayment method = payment.getMethod();
        ZonedDateTime createdAt = payment.getCreatedAt();

        return new PaymentCreatedResponse(
                eventPayment,
                paymentId,
                correlationId,
                customerId,
                amount,
                method,
                createdAt
        );
    }
}
