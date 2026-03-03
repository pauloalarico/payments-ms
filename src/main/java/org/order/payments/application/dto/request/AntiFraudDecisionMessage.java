package org.order.payments.application.dto.request;


import org.order.payments.domain.enums.MethodPayment;

import java.math.BigDecimal;

public record AntiFraudDecisionMessage (
        PaymentStatus paymentStatus,
        String id,
        String correlationId,
        String consumerId,
        BigDecimal amount,
        MethodPayment method,
        String riskFraud
){
}
