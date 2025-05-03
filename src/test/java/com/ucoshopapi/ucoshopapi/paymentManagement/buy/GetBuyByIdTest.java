package com.ucoshopapi.ucoshopapi.paymentManagement.buy;

import com.ucoshopapi.ucoshopapi.services.payment_management.BuyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetBuyByIdTest {

    private final BuyService buyService = new BuyService(null);

    @Test
    void getBuyById_ShouldThrowException_WhenIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            buyService.findById(null);
        });

        assertEquals("idBuy is null", exception.getMessage());
    }
}
