package com.ucoshopapi.ucoshopproccesantenclient.repositories.payment_management;

import com.ucoshopapi.ucoshopproccesantenclient.domain.payment_management.BankDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<BankDomain, UUID> {
}
