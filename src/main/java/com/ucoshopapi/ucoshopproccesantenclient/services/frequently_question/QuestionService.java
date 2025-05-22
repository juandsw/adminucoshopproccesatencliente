package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository userQuestionRepository;

    public QuestionService(QuestionRepository userQuestionRepository) {
        this.userQuestionRepository = userQuestionRepository;
    }

    public Map<String, Object> findAll() {
        List<DomainQuestion> questions = userQuestionRepository.findAll();
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
        Optional<DomainQuestion> question = userQuestionRepository.findById(id);
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
