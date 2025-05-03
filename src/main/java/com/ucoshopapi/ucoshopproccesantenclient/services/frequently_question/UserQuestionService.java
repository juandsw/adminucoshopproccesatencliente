package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.UserDomainQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.UserQuestionRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserQuestionService {

    private final UserQuestionRepository userQuestionRepository;

    public UserQuestionService(UserQuestionRepository userQuestionRepository) {
        this.userQuestionRepository = userQuestionRepository;
    }

    public Map<String, Object> findAll() {
        List<UserDomainQuestion> questions = userQuestionRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        if (questions.isEmpty()) {
            response.put("error", "No hay preguntas registradas");
        } else {
            response.put("mensaje", "Listado de preguntas consultado exitosamente");
            response.put("datos", questions);
        }
        return response;
    }

    public Map<String, Object> findById(Long id) {
        Optional<UserDomainQuestion> question = userQuestionRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (question.isPresent()) {
            response.put("mensaje", "Detalle de la pregunta consultada");
            response.put("datos", question.get());
        } else {
            response.put("error", "La pregunta no existe");
        }
        return response;
    }
}
