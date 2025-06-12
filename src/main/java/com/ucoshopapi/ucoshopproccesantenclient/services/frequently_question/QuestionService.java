package com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question;

import com.ucoshopapi.ucoshopapi.domain.frequently_question.DomainQuestion;
import com.ucoshopapi.ucoshopapi.messenger.frequentlyQuestion.QuestionPublicador;
import com.ucoshopapi.ucoshopapi.repositories.frequently_question.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserQuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionPublicador questionPublicador;

    public UserQuestionService(QuestionRepository questionRepository, QuestionPublicador questionPublicador) {
        this.questionRepository = questionRepository;
        this.questionPublicador = questionPublicador;
    }

    public ResponseEntity<Map<String, Object>> findAll() {
        List<DomainQuestion> questions = questionRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        if (questions.isEmpty()) {
            response.put("mensaje", "No hay preguntas registradas.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("mensaje", "Listado de preguntas consultado exitosamente.");
            response.put("datos", questions);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<Map<String, Object>> findById(Long id) {
        Map<String, Object> response = new HashMap<>();
        if (id == null || id <= 0) {
            response.put("error", "El ID de la pregunta no es válido.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<DomainQuestion> question = questionRepository.findById(id);
        if (question.isPresent()) {
            response.put("mensaje", "Detalle de la pregunta consultada.");
            response.put("datos", question.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "La pregunta con ID " + id + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> saveQuestion(DomainQuestion question) {
        Map<String, Object> response = new HashMap<>();

        if (question == null || question.getTitulo() == null || question.getTitulo().trim().isEmpty()) {
            response.put("error", "El título de la pregunta no puede estar vacío.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (question.getDescripcion() == null || question.getDescripcion().trim().isEmpty()) {
            response.put("error", "La descripción de la pregunta no puede estar vacía.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            questionPublicador.execute(question, UUID.randomUUID().toString());
            response.put("mensaje", "Respuesta enviada exitosamente al publicador.");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("error", "Error interno del servidor al procesar la respuesta: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}