package io.endeios.reports.appLogic;

import io.endeios.reports.appLogic.exceptions.NoSuchOriginException;
import io.endeios.reports.appLogic.exceptions.NoSuchWidgetException;
import io.endeios.reports.appLogic.queries.WidgetRepository;
import io.endeios.reports.domain.DataPoint;
import io.endeios.reports.web.dto.Widget;
import io.endeios.reports.web.dto.WidgetData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WidgetService {

    private final WidgetRepository widgetRepository;

    public WidgetService(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }


    public List<Widget> getAll() {
        return new ArrayList<>(translateAll(widgetRepository.getAll()));
    }

    private List<Widget> translateAll(List<io.endeios.reports.domain.Widget> all) {
        return all.stream()
                .map(translateToDto())
                .collect(Collectors.toList());
    }

    private static Function<io.endeios.reports.domain.Widget, Widget> translateToDto() {
        return (w) -> new Widget(w.getOrigin(), w.getName());
    }

    public List<Widget> getWidgetsOf(String origin) {
        List<Widget> widgets = widgetRepository.getWidgetsOf(origin)
                .stream().map(translateToDto()).collect(Collectors.toList());
        if(widgets.size()==0)
            throw new NoSuchOriginException(origin);

        return widgets;
    }

    public WidgetData getWidgetData(String origin, String name) {
        List<io.endeios.reports.domain.DataPoint> points = widgetRepository.getWidgetData(origin, name);
        if(points.size()==0)
            throw new NoSuchWidgetException(origin, name);

        return widgetDataFor(origin, name, points);
    }

    private WidgetData widgetDataFor(String origin, String name, List<io.endeios.reports.domain.DataPoint> points) {
        return new WidgetData(origin, name, translateToDataPointDto(points) );
    }

    private List<io.endeios.reports.web.dto.DataPoint> translateToDataPointDto(List<DataPoint> points) {

        return points
                .stream()
                .map((dp)-> new io.endeios.reports.web.dto.DataPoint(dp.getInstant(), dp.getValue()))
                .collect(Collectors.toList());
    }
}
