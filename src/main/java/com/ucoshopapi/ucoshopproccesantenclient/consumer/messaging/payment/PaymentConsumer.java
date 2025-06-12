package com.ucoshopapi.ucoshopproccesantenclient.consumer.messaging.payment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucoshopapi.ucoshopproccesantenclient.services.payment_management.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentConsumer {

    @Autowired
    private PaymentService paymentService;

    private final ObjectMapper objectMapper;

    public PaymentConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${client.payment.process.queue-name-patch}")
    public void receiveMessageUpdatePayment(String message) {
        try {
            System.out.println("Mensaje recibido para PATCH de Payment: " + message);

            Optional<Map<String, Object>> dataOptional = parseMessageToMap(message);
            if (dataOptional.isEmpty()) {
                System.err.println("Error: el mensaje no se pudo convertir a Map.");
                return;
            }

            Map<String, Object> data = dataOptional.get();

            if (!data.containsKey("idPayment")) {
                System.err.println("Error: El mensaje no contiene 'idPayment'");
                return;
            }

            UUID idPayment = UUID.fromString(data.get("idPayment").toString());
            data.remove("idPayment"); // Eliminamos el ID para no sobrescribirlo

            ResponseEntity<String> response = paymentService.updatePaymentByParams(idPayment, data);
            System.out.println("Resultado de la actualización: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje PATCH de Payment:");
            e.printStackTrace();
        }
    }

    private Optional<Map<String, Object>> parseMessageToMap(String message) {
        try {
            return Optional.of(objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {}));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
