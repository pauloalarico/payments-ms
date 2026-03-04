package org.order.payments.domain.repository;

import org.order.payments.domain.model.Payment;

import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);

    Payment findPaymentByPaymentId(UUID id);

    Payment findPaymentByCorrelationId(UUID id);
}
