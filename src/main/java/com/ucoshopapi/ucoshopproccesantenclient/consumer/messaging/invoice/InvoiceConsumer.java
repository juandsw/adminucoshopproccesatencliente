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
    public void saveInvoice(String messageBody) {
            Optional<Invoice> invoiceOpt = mapper.ejecutar(messageBody, Invoice.class);


                Invoice invoice = invoiceOpt.get();
                invoiceService.saveInvoice(invoice);
                log.info("Save " + invoice);

    }

    @RabbitListener(queues = {"apiclient.process.patch.client.qu"})
    public void updateInvoice(String messageBody) {

            Optional<Invoice> invoiceOpt = mapper.ejecutar(messageBody, Invoice.class);

                Invoice invoice = invoiceOpt.get();
                invoiceService.patchInvoiceDate(invoice.getIdInvoice(), invoice.getDate());
                log.info("Patch " + invoice);


    }

    @RabbitListener(queues = {"apiclient.process.delete.client.qu"})
    public void deleteInvoice(String messageBody) {

            Optional<Invoice> invoiceOpt = mapper.ejecutar(messageBody, Invoice.class);

                Invoice invoice = invoiceOpt.get();
                invoiceService.deleteInvoice(invoice.getIdInvoice());
                log.info("Delete " + invoice);

    }
}