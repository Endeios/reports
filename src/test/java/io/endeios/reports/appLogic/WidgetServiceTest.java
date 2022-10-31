package io.endeios.reports.appLogic;

import io.endeios.reports.appLogic.exceptions.NoSuchOriginException;
import io.endeios.reports.appLogic.exceptions.NoSuchWidgetException;
import io.endeios.reports.appLogic.queries.WidgetRepository;
import io.endeios.reports.web.dto.DataPoint;
import io.endeios.reports.web.dto.Widget;
import io.endeios.reports.web.dto.WidgetData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
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

        List<io.endeios.reports.domain.Widget> widgets = new ArrayList<>();
        addWidgetToList(widgets, "weatherStation", "temperature");
        addWidgetToList(widgets, "weatherStation", "humidity");
        addWidgetToList(widgets, "weatherStation", "hpa");
        when(widgetRepo.getAll()).thenReturn(widgets);

        List<Widget> dtoWidgets = widgetService.getAll();
        assertThat(dtoWidgets.size()).isEqualTo(3);
        Widget expectedWidget = new Widget("weatherStation", "temperature");
        assertThat(dtoWidgets).contains(expectedWidget);

    }


    @Test
    void returnExceptionWhenTheWidgetsThereAreNoWidgetOfThatOrigin() {


        String missingOrigin = "someOrigin";
        when(widgetRepo.getWidgetsOf(missingOrigin)).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(NoSuchOriginException.class, ()-> {
            widgetService.getWidgetsOf(missingOrigin);
        });
        assertTrue(exception.getMessage().contains("not an origin called " + missingOrigin));
    }

    @Test
    void returnWidgetsWhenAskedForTheCorrectOrigin() {
        String origin = "origin";
        when(widgetRepo.getWidgetsOf(origin)).thenReturn(List.of(new io.endeios.reports.domain.Widget("origin","name")));
        List<Widget> widgets = widgetService.getWidgetsOf(origin);
        assertThat(widgets.size()).isEqualTo(1);
        assertThat(widgets.get(0).getName()).isEqualTo("name");
        assertThat(widgets.get(0).getOrigin()).isEqualTo("origin");
    }

    @Test
    void returnExceptionWhenTheWidgetDataIsNotThere() {

        String origin = "origin";
        String name = "name";

        when(widgetRepo.getWidgetData(origin, name)).thenThrow(new NoSuchWidgetException(origin, name));

        Exception exception = assertThrows(NoSuchWidgetException.class, ()-> {
            widgetService.getWidgetData(origin, name);
        });
        Assertions.assertTrue(exception.getMessage().contains("widget in "+origin+"/"+name));
    }

    @Test
    void returnWidgetDataWhenTheWidgetDataIsThere() {

        String origin = "origin";
        String name = "name";

        when(widgetRepo.getWidgetData(origin, name)).thenReturn(List.of(
                new io.endeios.reports.domain.DataPoint(Instant.EPOCH, BigDecimal.valueOf(0L))
        ));

        WidgetData data = widgetService.getWidgetData(origin, name);
        assertThat(data.getName()).isEqualTo(name);
        assertThat(data.getOrigin()).isEqualTo(origin);
        DataPoint epoch =new DataPoint(Instant.EPOCH, BigDecimal.valueOf(0L));
        assertThat(data.getPoints()).contains(epoch);
    }

    private static void addWidgetToList(List<io.endeios.reports.domain.Widget> widgets, String weatherStation, String temperature) {
        widgets.add(new io.endeios.reports.domain.Widget(weatherStation, temperature));
    }
}