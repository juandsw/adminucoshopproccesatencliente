package com.ucoshopapi.ucoshopproccesantenclient.services.pqrs;

import com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs.MessageDomain;
import com.ucoshopapi.ucoshopproccesantenclient.domain.pqrs.PQRSDomain;
import com.ucoshopapi.ucoshopproccesantenclient.repositories.pqrs.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDomain> findMessageByPqrsId(PQRSDomain pqrsId) {
        return messageRepository.findByPqrsId(pqrsId);
    }

    public MessageDomain sendMessage(MessageDomain messageDomain) {
        messageDomain.setTimestamp(new Date());
        return messageRepository.save(messageDomain);
    }
}
