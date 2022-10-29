package io.endeios.reports.appLogic;

import io.endeios.reports.appLogic.exceptions.EmptyMessageException;
import io.endeios.reports.appLogic.exceptions.InvalidMessageException;

public class MessageHandler {
    private final EventSaver eventSaver;

    public MessageHandler(EventSaver eventSaver) {
        this.eventSaver = eventSaver;
    }

    public void saveMessage(String message){
        if(message==null)
            throw new EmptyMessageException("The received message is null");
        if(message.equals(""))
            throw new EmptyMessageException("The received message is empty");

        try {
            ReceivedEvent event = ReceivedEvent.fromString(message);
            eventSaver.saveEvent(event);
        }catch (Exception exception) {
            throw new InvalidMessageException("The received message is invalid");
        }
    }
}
