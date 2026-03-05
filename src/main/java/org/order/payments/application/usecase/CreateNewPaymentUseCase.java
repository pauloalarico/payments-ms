package org.order.payments.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.payments.application.dto.request.NewPaymentDTO;
import org.order.payments.application.dto.response.PaymentCreatedResponse;
import org.order.payments.domain.enums.MethodPayment;
import org.order.payments.domain.model.Payment;
import org.order.payments.domain.repository.PaymentRepository;
import org.order.payments.infra.kafka.producer.PaymentProducer;
import org.order.payments.infra.mapper.PaymentCreateResponseMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateNewPaymentUseCase {
    private final PaymentRepository repository;
    private final PaymentCreateResponseMapper mapper;
    private final PaymentProducer producer;

    public PaymentCreatedResponse createNewPayment(NewPaymentDTO dto) {
        String customerId = dto.consumerId();
        BigDecimal amount = dto.amount();
        MethodPayment method = dto.methodPayment();

        var payment = Payment.create(customerId, amount, method);
        repository.save(payment);

        PaymentCreatedResponse responseDto = mapper.toDto(payment);
        producer.send(responseDto);
        return responseDto;
    }
}
