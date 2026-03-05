package org.order.payments.infra.mapper;

import org.order.payments.application.dto.response.PaymentCreatedResponse;
import org.order.payments.domain.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentCreateResponseMapper {
    public PaymentCreatedResponse toDto(Payment payment) {

        return new PaymentCreatedResponse(
                payment.getEvent(),
                payment.getPaymentId(),
                payment.getCorrelationId(),
                payment.getCustomer(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getCreatedAt()
        );
    }
}
