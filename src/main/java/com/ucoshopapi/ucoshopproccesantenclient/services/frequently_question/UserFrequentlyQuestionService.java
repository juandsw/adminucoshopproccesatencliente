package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.UserDomainFrequentlyQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.UserFrequentlyQuestionRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserFrequentlyQuestionService {

    private final UserFrequentlyQuestionRepository userFrequentlyQuestionRepository;

    public UserFrequentlyQuestionService(UserFrequentlyQuestionRepository userFrequentlyQuestionRepository) {
        this.userFrequentlyQuestionRepository = userFrequentlyQuestionRepository;
    }

    public Map<String, Object> findAll() {
        List<UserDomainFrequentlyQuestion> questions = userFrequentlyQuestionRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        if (questions.isEmpty()) {
            response.put("error", "No hay preguntas frecuentes registradas");
        } else {
            response.put("mensaje", "Listado de preguntas frecuentes consultado exitosamente");
            response.put("datos", questions);
        }
        return response;
    }

    public Map<String, Object> save(UserDomainFrequentlyQuestion question) {
        try {
            userFrequentlyQuestionRepository.save(question);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Pregunta frecuente agregada exitosamente");
            return response;
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "No se pudo agregar la pregunta frecuente");
            return errorResponse;
        }
    }
}

