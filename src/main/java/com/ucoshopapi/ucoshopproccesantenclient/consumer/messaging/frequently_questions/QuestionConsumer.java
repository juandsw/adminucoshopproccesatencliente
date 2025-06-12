package com.ucoshopapi.ucoshopproccesantenclient.consumer.messaging.frequently_questions;


import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.gson.MapperJsonObject;
import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question.QuestionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuestionConsumer {

    private final QuestionService questionService;
    private final MapperJsonObject mapperJsonObject;

    @Autowired
    public QuestionConsumer(QuestionService questionService, MapperJsonObject mapperJsonObject) {
        this.questionService = questionService;
        this.mapperJsonObject = mapperJsonObject;
    }

    @RabbitListener(queues = "${client.question.process.queue-name-save}")
    public void receiveMessageProcessAnswer(String message) {
        try {
            System.out.println("Llegó el mensaje de preguntas: " + message);
            Optional<DomainQuestion> domainAnswerOptional = ObjetoDeMensaje(message);
            if (domainAnswerOptional.isPresent()) {
                questionService.saveQuestion(domainAnswerOptional.get());
            } else {
                System.err.println("No se pudo deserializar el mensaje a DomainQuestion: " + message);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje de pregunta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Optional<DomainQuestion> ObjetoDeMensaje(String mensaje) {
        return mapperJsonObject.ejecutar(mensaje, DomainQuestion.class);
    }
}
