package com.ucoshopapi.ucoshopapi.publicity;

import com.ucoshopapi.ucoshopapi.domain.campaingns.Campaingns;
import com.ucoshopapi.ucoshopapi.domain.product_management.ProductDomain;
import com.ucoshopapi.ucoshopapi.domain.publicity.PublicityDomain;
import com.ucoshopapi.ucoshopapi.repositories.campaigns.CampaingnsRepository;
import com.ucoshopapi.ucoshopapi.repositories.product_management.ProductRepository;
import com.ucoshopapi.ucoshopapi.repositories.publicity.PublicityRepository;
import com.ucoshopapi.ucoshopapi.services.publicity.PublicityService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UpdatePublicityDateMismatchTest {

    private final PublicityRepository publicityRepository = mock(PublicityRepository.class);
    private final CampaingnsRepository campaignRepository = mock(CampaingnsRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final PublicityService publicityService = new PublicityService(
            publicityRepository, campaignRepository, productRepository
    );

    @Test
    void publicityDatesDoNotMatchCampaignTest() {
        UUID publicityId = UUID.randomUUID();
        UUID campaignId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        // Campaña en BD
        Campaingns campaign = new Campaingns();
        campaign.setId(campaignId);
        campaign.setStartDate(LocalDate.of(2025, 1, 1));
        campaign.setEndDate(LocalDate.of(2025, 1, 10));

        // Producto en BD
        ProductDomain product = new ProductDomain();
        product.setProductId(productId);

        // Publicidad existente
        PublicityDomain existing = new PublicityDomain();
        existing.setId(publicityId);

        // Publicidad actualizada con fechas distintas a la campaña
        PublicityDomain updated = new PublicityDomain();
        updated.setCampaign(campaign);
        updated.setProduct(product);
        updated.setStartDate(LocalDate.of(2025, 1, 2)); // No coincide con campaña
        updated.setEndDate(LocalDate.of(2025, 1, 9));   // No coincide con campaña

        when(publicityRepository.findById(publicityId)).thenReturn(Optional.of(existing));
        when(campaignRepository.findById(campaignId)).thenReturn(Optional.of(campaign));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Object> response = publicityService.updatePublicity(publicityId, updated);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Las fechas de la publicidad deben coincidir exactamente con las fechas de la campaña", response.getBody());
    }
}