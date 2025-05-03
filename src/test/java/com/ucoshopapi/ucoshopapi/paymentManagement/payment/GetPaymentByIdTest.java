package com.ucoshopapi.ucoshopapi.paymentManagement.payment;

import com.ucoshopapi.ucoshopapi.services.payment_management.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetPaymentByIdTest {

    private final PaymentService paymentService = new PaymentService(null, null);

    @Test
    void getPaymentById_ShouldThrowException_WhenIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.findById(null);
        });

        assertEquals("idPayment is null", exception.getMessage());
    }
}
