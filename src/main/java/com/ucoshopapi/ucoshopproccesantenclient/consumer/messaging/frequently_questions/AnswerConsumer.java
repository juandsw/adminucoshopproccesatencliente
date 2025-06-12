package com.ucoshopapi.ucoshopproccesantenclient.messenger.frequently_questions;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainAnswer;
import com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question.AnswerService;
import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.gson.MapperJsonObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnswerConsumer {

    @Autowired
    private AnswerService answerService;

    private final MapperJsonObject mapperJsonObject;

    public AnswerConsumer(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    @RabbitListener(queues = "${client.answer.process.queue-name-save}")
    public void receiveMessageProcessAnswer(String message) {
        try {
            System.out.println("Llegó el mensaje de respuesta: " + message);
            Optional<DomainAnswer> domainAnswerOptional = ObjetoDeMensaje(message);
            if (domainAnswerOptional.isPresent()) {
                answerService.saveAnswer(domainAnswerOptional.get());
            } else {
                System.err.println("No se pudo deserializar el mensaje a DomainAnswer: " + message);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje de respuesta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Optional<DomainAnswer> ObjetoDeMensaje(String mensaje) {
        return mapperJsonObject.ejecutar(mensaje, DomainAnswer.class);
    }
}