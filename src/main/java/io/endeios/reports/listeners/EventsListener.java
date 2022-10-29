package io.endeios.reports.listeners;

import io.endeios.reports.appLogic.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class EventsListener {

    private final MessageHandler messageHandler;

    public EventsListener(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void receiveEvent(String message){
        System.out.println("Received <" + message + ">");
        try {
            messageHandler.saveMessage(message);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
