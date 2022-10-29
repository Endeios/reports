package io.endeios.reports.appLogic;

import io.endeios.reports.domain.Event;
import io.endeios.reports.repo.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventSaverTest {

    private DateProvider dateProvider;
    private EventRepository eventRepo;
    private EventSaver eventSaver;

    @BeforeEach
    void setUp() {
        dateProvider = mock(DateProvider.class);
        eventRepo = mock(EventRepository.class);
        eventSaver = new EventSaver(eventRepo, dateProvider);
    }

    @Test
    void eventIsCorrectlyTranslated() {
        ReceivedEvent receivedEvent = new ReceivedEvent("sender", "name", 8923478);
        when(dateProvider.now()).thenReturn(new Date(0));
        eventSaver.saveEvent(receivedEvent);
        Event entity = new Event();
        entity.setId(null);
        entity.setEvent_times(new Date(0));
        entity.setOrigin("sender");
        entity.setName("name");
        entity.setValue(BigDecimal.valueOf(8923478.0));
        verify(eventRepo).save(entity);
    }
}