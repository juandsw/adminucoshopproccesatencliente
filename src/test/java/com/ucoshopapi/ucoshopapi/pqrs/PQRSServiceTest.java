package com.ucoshopapi.ucoshopapi.pqrs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ucoshopapi.ucoshopapi.domain.pqrs.PQRSDomain;
import com.ucoshopapi.ucoshopapi.repositories.pqrs.PQRSRepository;
import com.ucoshopapi.ucoshopapi.services.pqrs.PQRSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

class PQRSServiceTest {

    @Mock
    private PQRSRepository pqrsRepository;

    @InjectMocks
    private PQRSService pqrsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testClosePqrsSuccess() {
        PQRSDomain pqrs = PQRSDomain.build(1L, "Test", "Desc", "Queja", new Date(), "COD123", "Proceso", "user@mail.com");
        when(pqrsRepository.findById(1L)).thenReturn(Optional.of(pqrs));

        boolean result = pqrsService.closePqrs(1L, "COD123");

        assertTrue(result);
        assertEquals("Cerrado", pqrs.getStatus());
        verify(pqrsRepository).save(pqrs);
    }
}
