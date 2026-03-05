package org.order.payments.application.messaging;


import org.order.payments.domain.enums.MethodPayment;
import org.order.payments.domain.enums.PaymentStatus;

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
