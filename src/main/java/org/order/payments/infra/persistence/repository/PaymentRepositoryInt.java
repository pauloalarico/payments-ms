package org.order.payments.infra.persistence.repository;

import org.order.payments.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepositoryInt extends JpaRepository<Payment, UUID> {
    @Query("""
            select p from Payment p WHERE p.paymentId = :id
            """)
    Optional<Payment> findPaymentByPaymentId(@Param("id")String id);

    @Query("""
            select p from Payment p WHERE p.correlationId = :id
            """)
    Optional<Payment> findPaymentByCorrelationId(@Param("id") String id);
}
