package com.ucoshopapi.ucoshopapi.paymentManagement.bank;

import com.ucoshopapi.ucoshopapi.services.payment_management.BankService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetBankByIdTest {

    private final BankService bankService = new BankService(null);

    @Test
    void getBankById_ShouldThrowException_WhenIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankService.findById(null);
        });

        assertEquals("Id is null", exception.getMessage());
    }
}
