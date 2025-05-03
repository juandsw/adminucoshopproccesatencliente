package com.ucoshopapi.ucoshopproccesantenclient.repositories.pqrs;

import com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs.PQRSDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PQRSRepository extends JpaRepository<PQRSDomain, Long> {

    List<PQRSDomain> findByType(String type);

    List<PQRSDomain> findByTypeAndUserEmail(String type, String userEmail);

    Optional<PQRSDomain> findById(Long id);
}
