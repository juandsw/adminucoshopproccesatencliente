package com.ucoshopapi.ucoshopproccesantenclient.services.payment_management;

import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.BankDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.payment_management.BankRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankDomain> findAll() {
        return bankRepository.findAll();
    }

    public BankDomain findById(UUID id) {
        if(id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if(!bankRepository.existsById(id)) {
            throw new IllegalArgumentException("Bank does not exist");
        }

        return bankRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bank does not exist"));
    }

}
