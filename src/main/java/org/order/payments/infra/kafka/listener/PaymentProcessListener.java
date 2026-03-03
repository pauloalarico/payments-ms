package org.order.payments.infra.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.order.payments.application.dto.request.AntiFraudDecisionMessage;
import org.order.payments.application.usecase.ProcessAntiFraudUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessListener {

    private final ProcessAntiFraudUseCase processorAntiFraud;

    @KafkaListener(topics = "${apps.kafka.listener-antifraud}")
        public void execute(AntiFraudDecisionMessage message, Acknowledgment acknowledge) {
        processorAntiFraud.process(message);
        acknowledge.acknowledge();
        log.info("Payment processed with the antifraud service for correlationId: {}", message.correlationId());
    }

}
