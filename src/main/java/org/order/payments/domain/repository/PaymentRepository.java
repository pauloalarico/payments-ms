package org.order.payments.domain.repository;

import org.order.payments.domain.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);

    Payment findPaymentByPaymentId(String id);

    Payment findPaymentByCorrelationId(String id);
}
