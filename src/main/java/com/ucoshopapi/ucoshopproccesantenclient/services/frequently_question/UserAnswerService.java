package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.UserDomainAnswer;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.UserAnswerRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;

    public UserAnswerService(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    public Map<String, Object> findAnswersByQuestionId(Long preguntaId) {
        List<UserDomainAnswer> answers = userAnswerRepository.findByPreguntaId(preguntaId);
        Map<String, Object> response = new HashMap<>();
        if (answers.isEmpty()) {
            response.put("error", "No hay respuestas registradas");
        } else {
            response.put("mensaje", "Respuestas consultadas exitosamente");
            response.put("datos", answers);
        }
        return response;
    }

    public Map<String, String> saveAnswer(UserDomainAnswer userDomainAnswer) {
        userAnswerRepository.save(userDomainAnswer);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Respuesta agregada exitosamente");
        return response;
    }
}
