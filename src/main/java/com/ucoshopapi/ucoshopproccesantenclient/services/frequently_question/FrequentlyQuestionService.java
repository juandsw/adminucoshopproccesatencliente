package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainAnswerFrequently;
import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainFrequentlyQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.FrequentlyQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FrequentlyQuestionService {

    private final FrequentlyQuestionRepository userFrequentlyQuestionRepository;

    public FrequentlyQuestionService(FrequentlyQuestionRepository userFrequentlyQuestionRepository) {
        this.userFrequentlyQuestionRepository = userFrequentlyQuestionRepository;
    }

    public Map<String, Object> saveQuestions(DomainFrequentlyQuestion question) {
        Map<String, Object> response = new HashMap<>();
        if (question.getTitulo() == null || question.getTitulo().trim().isEmpty()) {
            response.put("error", "El título de la pregunta no puede estar vacío.");
            return response;
        }
        if (question.getProceso() == null || question.getProceso().trim().isEmpty()) {
            response.put("error", "El proceso de la pregunta no puede estar vacío.");
            return response;
        }
        if (question.getRespuestaFrecuente() == null || question.getRespuestaFrecuente().isEmpty()) {
            response.put("error", "Debe incluir al menos una respuesta frecuente.");
            return response;
        }

        try {
            for (DomainAnswerFrequently answer : question.getRespuestaFrecuente()) {
                answer.setFrequentlyQuestion(question);
            }
            DomainFrequentlyQuestion savedQuestion = userFrequentlyQuestionRepository.save(question);

            response.put("mensaje", "Pregunta frecuente guardada exitosamente.");
            response.put("idPreguntaFrecuente", savedQuestion.getId());
            return response;

        } catch (Exception e) {
            response.put("error", "No se pudo guardar la pregunta frecuente: " + e.getMessage());
            return response;
        }
    }

}

