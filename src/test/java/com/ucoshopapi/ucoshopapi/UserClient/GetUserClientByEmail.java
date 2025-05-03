package com.ucoshopapi.ucoshopapi.UserClient;

import com.ucoshopapi.ucoshopapi.services.user_client.UserClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetUserClientByEmail {

    private UserClientService userClientService = new UserClientService();

    @Test
    void getEmail_Throw_exceptionWhenEmailIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userClientService.findByEmail(null);
        });
        assertEquals("El email es null", exception.getMessage());
    }

}

