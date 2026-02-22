package org.order.payments.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.order.payments.domain.enums.MethodPayment;

import java.math.BigDecimal;

public record NewPaymentDTO(
        @NotBlank
        String consumerId,
        @Positive
        BigDecimal amount,
        @NotNull
        MethodPayment methodPayment
) {
}
