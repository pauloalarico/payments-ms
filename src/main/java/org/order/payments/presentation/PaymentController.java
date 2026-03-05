package org.order.payments.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.order.payments.application.dto.request.NewPaymentDTO;
import org.order.payments.application.dto.response.PaymentCreatedResponse;
import org.order.payments.application.usecase.CreateNewPaymentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final CreateNewPaymentUseCase createNewPayment;

    @PostMapping
    public ResponseEntity<PaymentCreatedResponse> create(@Valid @RequestBody NewPaymentDTO dto, UriComponentsBuilder ucb) {
        var dtoResponse = createNewPayment.createNewPayment(dto);
        var uri = ucb.path("/payments/{id}").buildAndExpand(dtoResponse.hashCode()).toUri();
        return ResponseEntity.created(uri).body(dtoResponse);
    }
}
