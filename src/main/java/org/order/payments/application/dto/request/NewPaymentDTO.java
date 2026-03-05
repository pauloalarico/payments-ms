package org.order.payments.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.order.payments.domain.enums.MethodPayment;

import java.math.BigDecimal;

public record NewPaymentDTO(
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9\\-]{1,64}$", message = "Invalid format of id")
        String consumerId,
        @Positive
        BigDecimal amount,
        @NotNull
        MethodPayment methodPayment
) {
}
