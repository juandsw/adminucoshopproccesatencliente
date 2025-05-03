package com.ucoshopapi.ucoshopproccesantenclient.services.pqrs;

import com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs.PQRSDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.pqrs.PQRSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class PQRSService {

    @Autowired
    private PQRSRepository pqrsRepository;

    public List<PQRSDomain> findByTypeService(String type) {
        return pqrsRepository.findByType(type);
    }

    public List<PQRSDomain> findByTypeAndUserEmailService(String type, String email) {
        return pqrsRepository.findByTypeAndUserEmail(type, email);
    }

    public Optional<PQRSDomain> findById(Long id) {
        return pqrsRepository.findById(id);
    }

    public boolean closePqrs(Long pqrsId, String verificationCode) {
        return pqrsRepository.findById(pqrsId)
                .filter(pqrs -> pqrs.getCode().equals(verificationCode))
                .map(pqrs -> {
                    pqrs.setStatus("Cerrado");
                    pqrsRepository.save(pqrs);
                    return true;
                })
                .orElse(false);
    }

    public PQRSDomain updateFields(Long pqrsId, Map<String, Object> fields) {
        PQRSDomain pqrsDomain = pqrsRepository.findById(pqrsId)
                .orElseThrow(() -> new RuntimeException("PQRS no encontrada con id: " + pqrsId));

        fields.forEach((fieldName, value) -> {
            try {
                Field field = PQRSDomain.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(pqrsDomain, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Campo inválido: " + fieldName, e);
            }
        });

        return pqrsRepository.save(pqrsDomain);
    }
}
