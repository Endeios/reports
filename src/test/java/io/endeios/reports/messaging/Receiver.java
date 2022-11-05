package io.endeios.reports.messaging;

import io.endeios.reports.domain.Event;
import jdk.jshell.EvalException;

public class Receiver {
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }

    public void receiveMessage(Event event){
        // throw new RuntimeException(":P");  // With this, the messages are left in the exchange, and I can inspect them
        System.out.println("Received <" + event + ">");
    }

}
