package com.ucoshopapi.ucoshopproccesantenclient.consumidor.mensajeria.pqrs;

import com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils.gson.MapperJsonObject;
import com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs.MessageDomain;
import com.ucoshopapi.ucoshopproccesantenclient.services.pqrs.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceiverPqrs {

    private final MessageService messageService;
    private final MapperJsonObject mapperJsonObject;

    public ReceiverPqrs(MessageService messageService, MapperJsonObject mapperJsonObject) {
        this.messageService = messageService;
        this.mapperJsonObject = mapperJsonObject;
    }

    @RabbitListener(queues = "${proccesatenclient.message.sendmessage-qu}")
    public void receiveMessageProcessClient(String message) {
        try {
            messageService.sendMessage(obtenerObjetoDeMensaje(message).get());
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Optional<MessageDomain> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObject.ejecutar(mensaje, MessageDomain.class);
    }

}
