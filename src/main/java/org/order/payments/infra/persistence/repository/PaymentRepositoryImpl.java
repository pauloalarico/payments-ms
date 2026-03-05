package org.order.payments.infra.persistence.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.order.payments.domain.model.Payment;
import org.order.payments.domain.repository.PaymentRepository;
import org.order.payments.presentation.exceptions.PaymentNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment findPaymentByPaymentId(UUID id) {
        return repository.findPaymentByPaymentId(id).orElseThrow(() -> new PaymentNotFoundException("Cannot found payment"));
    }

    @Override
    public Payment findPaymentByCorrelationId(UUID id) {
        return repository.findPaymentByCorrelationId(id).orElseThrow(() -> new PaymentNotFoundException("Correlation Id does not exists!"));
    }
}
