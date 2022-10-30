package io.endeios.reports.appLogic;

import io.endeios.reports.appLogic.queries.WidgetRepository;
import io.endeios.reports.domain.Event;
import io.endeios.reports.repo.EventRepository;
import io.endeios.reports.web.dto.Widget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WidgetServiceTest {


    private WidgetService widgetService;
    private WidgetRepository widgetRepo;

    @BeforeEach
    void setUp() {
        widgetRepo = mock(WidgetRepository.class);
        widgetService = new WidgetService(widgetRepo);
    }

    @Test
    void returnEmptyWhenNoWidget() {
        List<Widget> widgets =  widgetService.getAll();
        assertThat(widgets).isEmpty();
    }

    @Test
    void returnAWidgetWhenThereAreEventsFromANameAndOrigin() {

        List<Widget> widgets = new ArrayList<>();
        widgets.add(new Widget("weatherStation", "temperature"));
        widgets.add(new Widget( "weatherStation", "humidity"));
        widgets.add(new Widget( "weatherStation", "hpa"));
        when(widgetRepo.getAll()).thenReturn(widgets);

        widgets = widgetService.getAll();
        assertThat(widgets.size()).isEqualTo(3);
        Widget expectedWidget = new Widget("weatherStation", "temperature");
        assertThat(widgets).contains(expectedWidget);

    }
}