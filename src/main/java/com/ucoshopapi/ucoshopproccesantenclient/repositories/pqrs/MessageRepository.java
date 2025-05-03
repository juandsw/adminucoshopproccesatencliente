package com.ucoshopapi.ucoshopproccesantenclient.repositories.pqrs;

import com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs.MessageDomain;
import com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs.PQRSDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageDomain, Long> {

    List<MessageDomain> findByPqrsId(PQRSDomain pqrsId);
}
