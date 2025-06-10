package com.ucoshopapi.ucoshopproccesantenclient.consumer.messaging.invoice;

import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.gson.MapperJsonObjectJackson;
import com.ucoshopapi.ucoshopproccesantenclient.domain.invoice.Invoice;
import com.ucoshopapi.ucoshopproccesantenclient.services.invoice.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
public class InvoiceConsumer {

    private final InvoiceService invoiceService;
    private final MapperJsonObjectJackson mapper;

    public InvoiceConsumer(InvoiceService invoiceService, MapperJsonObjectJackson mapper) {
        this.invoiceService = invoiceService;
        this.mapper = mapper;
    }

    @RabbitListener(queues = {"apiclient.process.save.client.qu"})
    public String saveInvoice(String messageBody) {
        try {
            Optional<Invoice> invoiceOpt = mapper.ejecutar(messageBody, Invoice.class);

            if (invoiceOpt.isPresent()) {
                Invoice invoice = invoiceOpt.get();
                invoiceService.saveInvoice(invoice);
                log.info("Save " + invoice);
                return "OK";
            } else {
                String error = "No se pudo deserializar el mensaje a Invoice.";
                log.error(error);
                return error;
            }
        } catch (Exception ex) {
            String error = "Error al guardar factura: " + ex.getMessage();
            log.error(error, ex);
            return error;
        }
    }

    @RabbitListener(queues = {"apiclient.process.patch.client.qu"})
    public String updateInvoice(String messageBody) {
        try {
            Optional<Invoice> invoiceOpt = mapper.ejecutar(messageBody, Invoice.class);

            if (invoiceOpt.isPresent()) {
                Invoice invoice = invoiceOpt.get();
                invoiceService.patchInvoiceDate(invoice.getIdInvoice(), invoice.getDate());
                log.info("Patch " + invoice);
                return "OK";
            } else {
                String error = "No se pudo deserializar el mensaje a Invoice.";
                log.error(error);
                return error;
            }
        } catch (Exception ex) {
            String error = "Error al actualizar factura: " + ex.getMessage();
            log.error(error, ex);
            return error;
        }
    }

    @RabbitListener(queues = {"apiclient.process.delete.client.qu"})
    public String deleteInvoice(String messageBody) {
        try {
            Optional<Invoice> invoiceOpt = mapper.ejecutar(messageBody, Invoice.class);

            if (invoiceOpt.isPresent()) {
                Invoice invoice = invoiceOpt.get();
                invoiceService.deleteInvoice(invoice.getIdInvoice());
                log.info("Delete " + invoice);
                return "OK";
            } else {
                String error = "No se pudo deserializar el mensaje a Invoice.";
                log.error(error);
                return error;
            }
        } catch (Exception ex) {
            String error = "Error al eliminar factura: " + ex.getMessage();
            log.error(error, ex);
            return error;
        }
    }
}