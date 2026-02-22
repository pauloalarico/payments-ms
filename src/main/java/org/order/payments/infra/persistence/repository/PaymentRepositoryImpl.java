package org.order.payments.infra.persistence.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.order.payments.domain.model.Payment;
import org.order.payments.domain.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentRepositoryInt repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment findPaymentByPaymentId(String id) {
        return repository.findPaymentByPaymentId(id).orElseThrow(() -> new EntityNotFoundException("Cannot found payment"));
    }

    @Override
    public Payment findPaymentByCorrelationId(String id) {
        return repository.findPaymentByCorrelationId(id).orElseThrow(() -> new EntityNotFoundException("Correlation Id does not exists!"));
    }
}
