package io.endeios.reports.appLogic;



import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


class ReceivedEventTest {

    @Test
    public void getAValidMessage(){
        ReceivedEvent event = ReceivedEvent.fromString("sender:name:0.1");
        ReceivedEvent masterEvent = new ReceivedEvent("sender","name",0.1);
        assertEquals(masterEvent, event);
    }
}