package io.endeios.reports.listeners;

import org.springframework.stereotype.Component;

@Component
public class EventsListener {
    public void receiveEvent(String message){
        System.out.println("Received <" + message + ">");
    }
}
