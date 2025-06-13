package com.ucoshopapi.ucoshopproccesantenclient.crosscutting.utils;

public interface MessageSender <T> {
    void execute(T message, String idMessage);
}