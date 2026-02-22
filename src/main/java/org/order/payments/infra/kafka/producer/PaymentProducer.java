package org.order.payments.infra.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.order.payments.application.dto.response.PaymentCreatedResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${apps.kafka.paymentTopic}")
    private String paymentTopic;

    public void send(PaymentCreatedResponse dto) {
        kafkaTemplate.send(paymentTopic, dto.correlationId().toString(), dto);
    }
}
