package com.ucoshopapi.ucoshopapi.UserClient;

import com.ucoshopapi.ucoshopapi.domain.user.UserDomain;
import com.ucoshopapi.ucoshopapi.services.user_client.UserClientService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatchUserClient {

    private UserClientService userClientService = new UserClientService();

    @Test
    void UserClientPatchIsNull(){
        UserDomain user = new UserDomain();
        user.setEmail("Test@test.com");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            userClientService.patchProfileUser(user.getEmail(), Collections.emptyMap());
        });

        assertEquals("No se pudo actualizar el campo porque esta vacio", exception.getMessage());
    }
}
