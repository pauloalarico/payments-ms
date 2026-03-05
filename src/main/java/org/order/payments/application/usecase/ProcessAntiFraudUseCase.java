package org.order.payments.application.usecase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.order.payments.application.messaging.AntiFraudDecisionMessage;
import org.order.payments.domain.enums.PaymentStatus;
import org.order.payments.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessAntiFraudUseCase {

    private final PaymentRepository paymentRepository;

    @Transactional
    public void process(AntiFraudDecisionMessage message) {
        var payment = paymentRepository.findPaymentByCorrelationId(UUID.fromString(message.correlationId()));
        PaymentStatus status = message.paymentStatus();

        if (status == PaymentStatus.APPROVED) {
            payment.approve();
            paymentRepository.save(payment);

        } else {
            payment.reprove();
            paymentRepository.save(payment);
        }
    }

}
