package com.ucoshopapi.ucoshopproccesantenclient.services.payment_management;

import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.BuyDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.payment_management.BuyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BuyService {

    private final BuyRepository buyRepository;

    public BuyService(BuyRepository buyRepository) {
        this.buyRepository = buyRepository;
    }

    public List<BuyDomain> findAll() {
        return buyRepository.findAll();
    }

    public BuyDomain findById(UUID idBuy) {
        if (idBuy == null) {
            throw new IllegalArgumentException("idBuy is null");
        }

        return buyRepository.findById(idBuy)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + idBuy));
    }
}
