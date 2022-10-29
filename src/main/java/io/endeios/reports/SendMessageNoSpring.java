package io.endeios.reports;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.endeios.reports.config.MessageSystem;

public class SendMessageNoSpring {
    public static void main(String[] args) throws Exception {
        try (Channel channel = createConnection().createChannel()) {
            AMQP.BasicProperties props = new AMQP.BasicProperties()
                    .builder().contentType("text/plain")
                    .contentEncoding("UTF-8")
                    .priority(0)
                    .deliveryMode(2)
                            .build();
            System.out.println("Publishing");
            channel.basicPublish(MessageSystem.topicExchangeName, MessageSystem.routingKey, props, "test:test:0.1".getBytes());
            System.out.println("Published");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Connection createConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory.newConnection();
    }
}
