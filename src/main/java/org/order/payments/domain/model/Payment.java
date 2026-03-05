package org.order.payments.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.order.payments.domain.enums.EventPayment;
import org.order.payments.domain.enums.MethodPayment;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_payment", unique = true)
    private UUID paymentId;
    @Column(name = "cd_customer")
    private UUID customer;
    @Column(name = "cd_correlation", unique = true)
    private UUID correlationId;
    @Column(name = "vl_amount")
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "nm_method_payment")
    private MethodPayment method;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_payment")
    private EventPayment event;
    @Column(name = "dt_creation")
    private ZonedDateTime createdAt;


    public static Payment create(String customerId, BigDecimal amount, MethodPayment method) {
        var payment = new Payment();
        payment.correlationId = UUID.randomUUID();
        payment.customer = UUID.fromString(customerId);
        payment.amount = amount;
        payment.method = method;
        payment.createdAt = ZonedDateTime.now();
        payment.event = EventPayment.PAYMENT_REQUESTED;
        return payment;
    }

    public void approve() {
        this.event = EventPayment.PAYMENT_APPROVED;
    }

    public void reprove() {
        this.event = EventPayment.PAYMENT_REPROVED;
    }
}
