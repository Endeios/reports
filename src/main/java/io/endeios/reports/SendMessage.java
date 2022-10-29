package io.endeios.reports;

import io.endeios.reports.listeners.MessageSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class SendMessage implements CommandLineRunner {
    private static Logger LOG = LoggerFactory
            .getLogger(CommandLineRunner.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SendMessage.class, args);
        LOG.info("APPLICATION FINISHED");
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        for(int i=0;i<1000;i++) {
            System.out.println("Sending message... " + i);
            rabbitTemplate.convertAndSend(MessageSystem.topicExchangeName, MessageSystem.routingKey, "Hello from RabbitMQ!");
        }
    }

    private final RabbitTemplate rabbitTemplate;

    public SendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
