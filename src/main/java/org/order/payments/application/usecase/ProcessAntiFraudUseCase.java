package org.order.payments.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.payments.application.dto.request.AntiFraudDecisionMessage;
import org.order.payments.application.dto.request.PaymentStatus;
import org.order.payments.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessAntiFraudUseCase {

    private final PaymentRepository paymentRepository;

    public void process(AntiFraudDecisionMessage message) {
        var payment = paymentRepository.findPaymentByCorrelationId(message.correlationId());
        PaymentStatus status = message.paymentStatus();

        if (status == PaymentStatus.APPROVED) {
            payment.approve();
        }

        if (status == PaymentStatus.REPROVED) {
            payment.reprove();
        }
    }

}
