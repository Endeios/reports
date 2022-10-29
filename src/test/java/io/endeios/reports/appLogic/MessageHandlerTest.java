package io.endeios.reports.appLogic;

import io.endeios.reports.appLogic.exceptions.EmptyMessageException;
import io.endeios.reports.appLogic.exceptions.InvalidMessageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageHandlerTest {

    private MessageHandler messageHandler;
    private EventSaver eventSaver;

    @BeforeEach
    void setUp() {
        eventSaver = Mockito.mock(EventSaver.class);
        messageHandler = new MessageHandler(eventSaver);
    }

    @Test
    public void complainIfMessageIsEmpty(){
        Exception exception = assertThrows(EmptyMessageException.class, ()-> {
            messageHandler.saveMessage("");
        });
        assertTrue(exception.getMessage().contains("message is empty"));
    }
    @Test
    public void complainIfMessageIsNull(){
        Exception exception = assertThrows(EmptyMessageException.class, ()-> {
            messageHandler.saveMessage(null);
        });
        assertTrue(exception.getMessage().contains("message is null"));
    }
    @Test
    public void complainIfMessageIsInvalid(){
        Exception exception = assertThrows(InvalidMessageException.class, ()-> {
            messageHandler.saveMessage("sdfhjfksdhfjks");
        });
        assertTrue(exception.getMessage().contains("message is invalid"));
    }

    @Test
    public void correctMessageIsSaved(){
        messageHandler.saveMessage("sender:name:0.1");
        ReceivedEvent event = new ReceivedEvent("sender","name",0.1);
        Mockito.verify(eventSaver).saveEvent(event);
    }

}