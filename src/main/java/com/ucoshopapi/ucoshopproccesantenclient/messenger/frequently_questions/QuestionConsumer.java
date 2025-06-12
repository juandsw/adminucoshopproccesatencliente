package com.ucoshopapi.ucoshopproccesantenclient.messenger.frequently_questions;


import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.gson.MapperJsonObject;
import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question.UserQuestionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuestionConsumer {

    @Autowired
    private UserQuestionService userQuestionService;

    private final MapperJsonObject mapperJsonObject;

    public QuestionConsumer(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    @RabbitListener(queues = "${client.question.process.queue-name-save}")
    public void receiveMessageProcessQuestion(String message) {
        try {
            System.out.println("Llegó el mensaje de Pregunta: " + message);
            Optional<DomainQuestion> domainQuestionOptional = ObjetoDeMensaje(message);
            if (domainQuestionOptional.isPresent()) {
                userQuestionService.saveQuestion(domainQuestionOptional.get());
            } else {
                System.err.println("No se pudo deserializar el mensaje a DomainQuestion: " + message);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje de Pregunta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Optional<DomainQuestion> ObjetoDeMensaje(String mensaje) {
        return mapperJsonObject.ejecutar(mensaje, DomainQuestion.class);
    }
}