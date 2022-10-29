package io.endeios.reports.appLogic;

import io.endeios.reports.domain.Event;
import io.endeios.reports.repo.EventRepository;

import java.math.BigDecimal;
import java.sql.Date;

public class EventSaver {

    private final EventRepository eventRepository;
    private final DateProvider dateProvider;

    public EventSaver(EventRepository eventRepository, DateProvider dateProvider) {
        this.eventRepository = eventRepository;
        this.dateProvider = dateProvider;
    }


    public void saveEvent(ReceivedEvent event) {

        Event dbEvent = new Event();
        dbEvent.setEvent_times(dateProvider.now());
        dbEvent.setName(event.getName());
        dbEvent.setValue(BigDecimal.valueOf(event.getValue()));
        dbEvent.setOrigin(event.getSender());
        eventRepository.save(dbEvent);
    }
}
