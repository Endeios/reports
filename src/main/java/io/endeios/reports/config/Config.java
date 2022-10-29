package io.endeios.reports.config;

import io.endeios.reports.appLogic.DateProvider;
import io.endeios.reports.appLogic.EventSaver;
import io.endeios.reports.appLogic.MessageHandler;
import io.endeios.reports.repo.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {


    @Bean
    public MessageHandler messageHandler(EventSaver eventSaver){
        return new MessageHandler(eventSaver);
    }

    @Bean
    public EventSaver eventSaver(EventRepository eventRepository){
        return new EventSaver(eventRepository, new DateProvider());
    }
}
