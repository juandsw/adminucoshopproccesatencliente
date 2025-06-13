package com.ucoshopapi.ucoshopproccesantenclient.services.invoice;

import com.ucoshopapi.ucoshopproccesantenclient.domain.invoice.Invoice;
import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.PaymentDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.invoice.InvoiceRepository;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.payment_management.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final InvoiceRepository invoiceRepository;


    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(UUID idInvoice) {
        return validateInvoiceExistence(idInvoice);
    }

    @Transactional
    public void saveInvoice(Invoice invoice) {

        invoiceRepository.save(invoice);
    }


    public void deleteInvoice(UUID idInvoice) {

        invoiceRepository.deleteById(idInvoice);
    }

    public void patchInvoiceDate(UUID currentId, Date newDate) {

        Invoice invoice = new Invoice();
        invoice.setDate(newDate);
        invoiceRepository.save(invoice);

    }

    private Invoice validateInvoiceExistence(UUID currentId) {
        return invoiceRepository.findById(currentId).orElseThrow(() -> new EntityNotFoundException("La factura no existe"));
    }


}