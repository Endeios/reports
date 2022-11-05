package io.endeios.reports.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.jms.*;
import java.net.URI;

public class JMSTests {

    private URI brokerUri;
    private BrokerService broker;

    @BeforeEach
    void setUp() throws Exception {
        String brokerURI = "tcp://localhost:61616";
        brokerUri = new URI(brokerURI);
        broker = BrokerFactory.createBroker("broker:"+ brokerUri);
        broker.setBrokerName("Constantine");
        broker.start();
    }

    @AfterEach
    void tearDown() throws Exception {
        broker.stop();
    }

    @Test
    void name() throws JMSException {
        // The producer and consumer need to get a connection factory and use it to set up
        // a connection and a session
        QueueConnectionFactory connFactory = new ActiveMQConnectionFactory(brokerUri); //new com.sun.messaging.QueueConnectionFactory();
        QueueConnection conn = connFactory.createQueueConnection();
        // This session is not transacted, and it uses automatic message acknowledgement
        QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue q = new ActiveMQQueue("destination");
        // Sender
        QueueSender sender = session.createSender(q);
        // Text message
        TextMessage msg = session.createTextMessage();
        msg.setText("Hello there!");
        System.out.println("Sending the message: "+msg.getText());
        sender.send(msg);
        // Receiver
        QueueReceiver receiver = session.createReceiver(q);
        conn.start();
        Message m = receiver.receive();
        if(m instanceof TextMessage) {
            TextMessage txt = (TextMessage) m;
            System.out.println("Message Received: "+txt.getText());
        }
        session.close();
        conn.close();
    }
}
