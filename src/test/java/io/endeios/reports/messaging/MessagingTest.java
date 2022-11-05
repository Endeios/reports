package io.endeios.reports.messaging;

import io.endeios.reports.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.PooledChannelConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MessagingContext.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, LiquibaseAutoConfiguration.class})
@TestPropertySource("classpath:application-messaging.yaml")
public class MessagingTest {

    @Autowired
   private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Test
    void haveNotNUllCF() {
        assertThat(connectionFactory).isNotNull();
    }

    @Test
    void sendVariousMessages() {
        Event event = new Event();
        event.setName("name");
        event.setId(1L);
        event.setValue(BigDecimal.valueOf(2L));
        event.setOrigin("origin");
        rabbitTemplate.convertAndSend(MessagingContext.topicExchangeName,"foo.bar.message",event );
        rabbitTemplate.convertAndSend(MessagingContext.topicExchangeName,"foo.bar.message",event );
        rabbitTemplate.convertAndSend(MessagingContext.topicExchangeName,"foo.bar.message",event );
        rabbitTemplate.convertAndSend(MessagingContext.topicExchangeName,"foo.bar.message",event );
        rabbitTemplate.convertAndSend(MessagingContext.topicExchangeName,"foo.bar.message",event );
        rabbitTemplate.convertAndSend(MessagingContext.topicExchangeName,"foo.bar.message",event );

    }
}
