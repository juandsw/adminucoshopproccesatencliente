package com.ucoshopapi.ucoshopproccesantenclient.repositories.invoice;

import com.ucoshopapi.ucoshopproccesantenclient.domain.invoice.Invoice;
import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.PaymentDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    boolean existsByPayment(PaymentDomain payment);
}