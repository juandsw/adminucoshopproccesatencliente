package com.ucoshopapi.ucoshopproccesantenclient.services.invoice;

import com.ucoshopapi.ucoshopproccesantenclient.domain.invoice.Invoice;
import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.PaymentDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.invoice.InvoiceRepository;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.payment_management.PaymentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
@Transactional
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(UUID idInvoice) {
        return validateInvoiceExistence(idInvoice);
    }

    @Transactional
    public void saveInvoice(Invoice invoice) {

        UUID paymentId = invoice.getPayment().getIdPayment();

        PaymentDomain payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("El pago ingresado no existe en el sistema"));

        if (invoiceRepository.existsByPayment(payment)) {
            throw new RuntimeException("Este pago ya tiene una factura asociada");
        }

        invoice.setPayment(payment);

        invoiceRepository.save(invoice);
    }


    public void deleteInvoice(UUID idInvoice) {

        if (!invoiceRepository.existsById(idInvoice)) {
            throw new RuntimeException("No se encontró la factura con ID: " + idInvoice);
        }
        invoiceRepository.deleteById(idInvoice);
    }

    public Invoice patchInvoiceDate(UUID currentId, Date newDate) {

        Invoice invoice = findById(currentId);
        logger.info("Fecha antes de la actualización: {}", invoice.getDate());

        invoice.setDate(newDate);
        invoiceRepository.saveAndFlush(invoice);

        logger.info("Fecha después de la actualización: {}", invoice.getDate());
        return invoice;
    }

    public Invoice validateInvoiceExistence(UUID currentId) {
        return invoiceRepository.findById(currentId).orElseThrow(() -> new RuntimeException("La factura no existe"));
    }
}