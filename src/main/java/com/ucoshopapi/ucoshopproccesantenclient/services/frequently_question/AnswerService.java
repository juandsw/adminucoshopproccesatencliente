package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainAnswer;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.AnswerRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerService {

    private final AnswerRepository userAnswerRepository;

    public AnswerService(AnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    public Map<String, Object> findAnswersByQuestionId(Long preguntaId) {
        List<DomainAnswer> answers = userAnswerRepository.findByPreguntaId(preguntaId);
        Map<String, Object> response = new HashMap<>();
        if (answers.isEmpty()) {
            response.put("error", "No hay respuestas registradas");
        } else {
            response.put("mensaje", "Respuestas consultadas exitosamente");
            response.put("datos", answers);
        }
        return response;
    }

    public Map<String, String> saveAnswer(DomainAnswer userDomainAnswer) {
        userAnswerRepository.save(userDomainAnswer);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Respuesta agregada exitosamente");
        return response;
    }
}
