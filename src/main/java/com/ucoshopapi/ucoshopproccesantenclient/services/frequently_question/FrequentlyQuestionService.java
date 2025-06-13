package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;


import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainFrequentlyQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.FrequentlyQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FrequentlyQuestionService {

    private final FrequentlyQuestionRepository repository;

    public FrequentlyQuestionService(FrequentlyQuestionRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> findAll() {
        List<DomainFrequentlyQuestion> questions = repository.findAll();
        Map<String, Object> response = new HashMap<>();
        if (questions.isEmpty()) {
            response.put("error", "No hay preguntas frecuentes registradas");
        } else {
            response.put("mensaje", "Listado de preguntas frecuentes consultado exitosamente");
            response.put("datos", questions);
        }
        return response;
    }

    public Map<String, Object> save(DomainFrequentlyQuestion question) {
        Map<String, Object> response = new HashMap<>();
        try {
            DomainFrequentlyQuestion saved = repository.save(question);
            response.put("mensaje", "Pregunta frecuente guardada exitosamente");
            response.put("frequentlyQuestion", saved);
        } catch (Exception e) {
            response.put("error", "No se pudo guardar la pregunta frecuente: " + e.getMessage());
        }
        return response;
    }
}
