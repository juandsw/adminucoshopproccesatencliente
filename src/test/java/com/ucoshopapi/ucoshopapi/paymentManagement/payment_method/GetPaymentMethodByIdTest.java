package com.ucoshopapi.ucoshopapi.paymentManagement.payment_method;

import com.ucoshopapi.ucoshopapi.services.payment_management.PaymentMethodService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetPaymentMethodByIdTest {

    private final PaymentMethodService paymentMethodService = new PaymentMethodService(null);

    @Test
    void getPaymentMethodById_ShouldThrowException_WhenIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentMethodService.findById(null);
        });

        assertEquals("idPaymentMethod is null", exception.getMessage());
    }


}
