package com.ucoshopapi.ucoshopproccesantenclient.services.payment_management;

import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.PaymentMethodDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.payment_management.PaymentMethodRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<PaymentMethodDomain> findAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethodDomain findById(UUID idPaymentMethod) {
        if (idPaymentMethod == null) {
            throw new IllegalArgumentException("idPaymentMethod is null");
        }

        return paymentMethodRepository.findById(idPaymentMethod)
                .orElseThrow(() -> new IllegalArgumentException("Método de pago no encontrado con ID: " + idPaymentMethod));
    }
}
