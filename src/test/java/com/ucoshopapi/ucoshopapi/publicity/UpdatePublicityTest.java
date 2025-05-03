package com.ucoshopapi.ucoshopapi.publicity;

import com.ucoshopapi.ucoshopapi.domain.publicity.PublicityDomain;
import com.ucoshopapi.ucoshopapi.repositories.campaigns.CampaingnsRepository;
import com.ucoshopapi.ucoshopapi.repositories.product_management.ProductRepository;
import com.ucoshopapi.ucoshopapi.repositories.publicity.PublicityRepository;
import com.ucoshopapi.ucoshopapi.services.publicity.PublicityService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UpdatePublicityTest {

    private final PublicityRepository publicityRepository = mock(PublicityRepository.class);
    private final CampaingnsRepository campaignRepository = mock(CampaingnsRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final PublicityService publicityService = new PublicityService(
            publicityRepository, campaignRepository, productRepository
    );

    @Test
    void publicityDoesNotExistTest() {
        UUID publicityId = UUID.randomUUID();
        PublicityDomain updatedData = new PublicityDomain(); // Sin importar el contenido, no existe en BD

        when(publicityRepository.findById(publicityId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = publicityService.updatePublicity(publicityId, updatedData);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("La publicidad no existe", response.getBody());
    }
}