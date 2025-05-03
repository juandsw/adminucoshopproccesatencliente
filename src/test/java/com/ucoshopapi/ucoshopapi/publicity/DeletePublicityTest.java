package com.ucoshopapi.ucoshopapi.publicity;

import com.ucoshopapi.ucoshopapi.repositories.campaigns.CampaingnsRepository;
import com.ucoshopapi.ucoshopapi.repositories.product_management.ProductRepository;
import com.ucoshopapi.ucoshopapi.repositories.publicity.PublicityRepository;
import com.ucoshopapi.ucoshopapi.services.publicity.PublicityService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DeletePublicityTest {

    private final PublicityRepository publicityRepository = mock(PublicityRepository.class);
    private final CampaingnsRepository campaignRepository = mock(CampaingnsRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final PublicityService publicityService = new PublicityService(
            publicityRepository, campaignRepository, productRepository
    );

    @Test
    void publicityDoesNotExistTest() {
        UUID publicityId = UUID.randomUUID();

        when(publicityRepository.existsById(publicityId)).thenReturn(false);

        ResponseEntity<String> response = publicityService.deletePublicity(publicityId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("La publicidad no existe", response.getBody());
    }

    @Test
    void publicityDeletedSuccessfullyTest() {
        UUID publicityId = UUID.randomUUID();

        when(publicityRepository.existsById(publicityId)).thenReturn(true);

        ResponseEntity<String> response = publicityService.deletePublicity(publicityId);

        verify(publicityRepository).deleteById(publicityId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Publicidad eliminada exitosamente", response.getBody());
    }
}