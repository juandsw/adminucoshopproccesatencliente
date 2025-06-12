package com.ucoshopapi.ucoshopproccesantenclient.consumer.messaging.frequently_questions;

import com.ucoshopapi.ucoshopproccesantenclient.domain.frequently_question.DomainFrequentlyQuestion;
import com.ucoshopapi.ucoshopproccesantenclient.services.frequently_question.FrequentlyQuestionService;
import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.gson.MapperJsonObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FrequentlyQuestionConsumer {

    @Autowired
    private FrequentlyQuestionService frequentlyQuestionService;

    private final MapperJsonObject mapperJsonObject;

    public FrequentlyQuestionConsumer(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    @RabbitListener(queues = "${client.frequentlyquestion.process.queue-name-save}")
    public void receiveMassegeProcessFrequentlyQuestion(String message) {
        try {
            System.out.println("Llego el mensaje: " + message);
            frequentlyQuestionService.saveQuestions(ObjetoDeMensaje(message).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Optional<DomainFrequentlyQuestion> ObjetoDeMensaje(String mensaje) {
        return mapperJsonObject.ejecutar(mensaje, DomainFrequentlyQuestion.class);
    }
}