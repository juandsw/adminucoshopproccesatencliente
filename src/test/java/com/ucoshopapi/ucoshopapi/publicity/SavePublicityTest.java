package com.ucoshopapi.ucoshopapi.publicity;

import com.ucoshopapi.ucoshopapi.domain.publicity.PublicityDomain;
import com.ucoshopapi.ucoshopapi.repositories.campaigns.CampaingnsRepository;
import com.ucoshopapi.ucoshopapi.repositories.product_management.ProductRepository;
import com.ucoshopapi.ucoshopapi.repositories.publicity.PublicityRepository;
import com.ucoshopapi.ucoshopapi.services.publicity.PublicityService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SavePublicityTest {

    private final PublicityRepository publicityRepository = mock(PublicityRepository.class);
    private final CampaingnsRepository campaignRepository = mock(CampaingnsRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final PublicityService publicityService = new PublicityService(
            publicityRepository, campaignRepository, productRepository
    );

    @Test
    void publicityDataIsNullTest() {
        PublicityDomain publicity = new PublicityDomain(); // No tiene campaña ni producto

        ResponseEntity<Object> response = publicityService.createPublicity(publicity);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Los datos de la publicidad no pueden ser nulos", response.getBody());
    }
}