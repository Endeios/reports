package io.endeios.reports.config;

import io.endeios.reports.appLogic.DateProvider;
import io.endeios.reports.appLogic.EventSaver;
import io.endeios.reports.appLogic.MessageHandler;
import io.endeios.reports.appLogic.WidgetService;
import io.endeios.reports.appLogic.queries.WidgetRepository;
import io.endeios.reports.repo.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

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

    @Bean
    public WidgetService widgetService(WidgetRepository widgetRepo){
        return new WidgetService(widgetRepo);
    }

    @Bean WidgetRepository widgetRepository(JdbcTemplate jdbcTemplate){
        return new WidgetRepository(jdbcTemplate);
    }
}
