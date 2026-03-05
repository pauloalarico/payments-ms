package org.order.payments.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.payments.application.messaging.AntiFraudDecisionMessage;
import org.order.payments.domain.enums.PaymentStatus;
import org.order.payments.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        } else {
            payment.reprove();
        }
        paymentRepository.save(payment);
    }

}
