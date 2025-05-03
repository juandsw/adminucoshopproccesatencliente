package com.ucoshopapi.ucoshopproccesantenclient.services.payment_management;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.PaymentDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.payment_management.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;

    public PaymentService(PaymentRepository paymentRepository, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.objectMapper = objectMapper;
    }

    public List<PaymentDomain> findAll() {
        return paymentRepository.findAll();
    }

    public PaymentDomain findById(UUID idPayment) {
        if (idPayment == null) {
            throw new IllegalArgumentException("idPayment is null");
        }

        return paymentRepository.findById(idPayment)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + idPayment));
    }

    public ResponseEntity<String> updatePaymentByParams(UUID id, Map<String, Object> data) throws JsonMappingException {
        if (!paymentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El payment no existe");
        }

        PaymentDomain payment = paymentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No se encontró el método de pago con el ID proporcionado")
        );

        objectMapper.updateValue(payment, data);
        paymentRepository.save(payment);

        return ResponseEntity.ok("Se modificó correctamente el método de pago");
    }



}
