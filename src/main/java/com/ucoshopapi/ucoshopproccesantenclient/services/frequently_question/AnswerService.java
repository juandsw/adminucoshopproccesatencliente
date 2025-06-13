package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;


import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainAnswer;
import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.AnswerRepository;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.frequently_question.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<Map<String, Object>> findAnswersByQuestionId(Long preguntaId) {
        Map<String, Object> response = new HashMap<>();

        if (preguntaId == null || preguntaId <= 0) {
            response.put("error", "El ID de la pregunta no es válido.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<DomainQuestion> question = questionRepository.findById(preguntaId);
        if (!question.isPresent()) {
            response.put("error", "La pregunta con ID " + preguntaId + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<DomainAnswer> answers = answerRepository.findByQuestion_Id(preguntaId);
        if (answers.isEmpty()) {
            response.put("mensaje", "No hay respuestas registradas para la pregunta con ID " + preguntaId + ".");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("mensaje", "Respuestas consultadas exitosamente.");
            response.put("datos", answers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<Map<String, Object>> saveAnswer(DomainAnswer domainAnswer) {
        Map<String, Object> response = new HashMap<>();

        if (domainAnswer == null || domainAnswer.getQuestion() == null || domainAnswer.getQuestion().getId() == null || domainAnswer.getQuestion().getId() <= 0) {
            response.put("error", "El ID de la pregunta no es válido.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (domainAnswer.getRespuesta() == null || domainAnswer.getRespuesta().trim().isEmpty()) {
            response.put("error", "El texto de la respuesta no puede estar vacío.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<DomainQuestion> questionOptional = questionRepository.findById(domainAnswer.getQuestion().getId());
        if (!questionOptional.isPresent()) {
            response.put("error", "La pregunta con ID " + domainAnswer.getQuestion().getId() + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            domainAnswer.setQuestion(questionOptional.get());

            answerRepository.save(domainAnswer);

            response.put("mensaje", "Respuesta guardada exitosamente.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", "Error interno del servidor al guardar la respuesta: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}