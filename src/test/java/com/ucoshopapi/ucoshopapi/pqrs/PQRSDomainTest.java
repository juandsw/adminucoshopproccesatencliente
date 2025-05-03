package com.ucoshopapi.ucoshopapi.pqrs;

import com.ucoshopapi.ucoshopapi.domain.pqrs.PQRSDomain;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PQRSDomainTest {

    @Test
    void testBuildPqrsDomain() {
        Date date = new Date();
        PQRSDomain pqrs = PQRSDomain.build(
                1L,
                "Título",
                "Descripción",
                "Queja",
                date,
                "ABC123",
                "Proceso1",
                "usuario@correo.com"
        );

        assertEquals("Título", pqrs.getTitle());
        assertEquals("Queja", pqrs.getType());
        assertEquals("Abierto", pqrs.getStatus());
        assertEquals("usuario@correo.com", pqrs.getUserEmail());
        assertEquals(date, pqrs.getDateOfCreation());
    }
}
