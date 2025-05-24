package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainAnswerFrequently;
import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainFrequentlyQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.FrequentlyQuestionRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FrequentlyQuestionService {

    private final FrequentlyQuestionRepository frequentlyQuestionRepository;

    public FrequentlyQuestionService(FrequentlyQuestionRepository frequentlyQuestionRepository) {
        this.frequentlyQuestionRepository = frequentlyQuestionRepository;
    }

    @Transactional
    public Map<String, Object> saveFrequentlyQuestion(DomainFrequentlyQuestion question) {
        try {
            if (question.getRespuestaFrecuente() != null && !question.getRespuestaFrecuente().isEmpty()) {
                for (DomainAnswerFrequently answer : question.getRespuestaFrecuente()) {
                    answer.setFrequentlyQuestion(question);
                }
            }

            DomainFrequentlyQuestion savedQuestion = frequentlyQuestionRepository.save(question);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Pregunta frecuente agregada/actualizada exitosamente");
            response.put("datos", savedQuestion);
            return response;
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "No se pudo agregar/actualizar la pregunta frecuente: " + e.getMessage());
            e.printStackTrace();
            return errorResponse;
        }
    }

    @Transactional(readOnly = true)
    public List<DomainFrequentlyQuestion> getAllFrequentlyQuestions() {
        return frequentlyQuestionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DomainFrequentlyQuestion> getFrequentlyQuestionById(Long id) {
        return frequentlyQuestionRepository.findById(id);
    }
}
