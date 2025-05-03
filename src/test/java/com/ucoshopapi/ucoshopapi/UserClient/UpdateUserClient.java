package com.ucoshopapi.ucoshopapi.UserClient;

import com.ucoshopapi.ucoshopapi.domain.user.UserDomain;
import com.ucoshopapi.ucoshopapi.services.user_client.UserClientService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateUserClient {


    private UserClientService userClientService = new UserClientService();

    @Test
    void UpdateClient_Throw_ExceptionWhen_AnyFieldIsEmpty() {
        UserDomain user = new UserDomain();
        user.setEmail("Test@test.com");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            userClientService.updateClient(user.getEmail(),null);
        });
        assertEquals("No se pudo actualizar el usuario, alguno de los campos es null",exception.getMessage());
    }
}
