package io.endeios.reports.appLogic;

import io.endeios.reports.appLogic.queries.WidgetRepository;
import io.endeios.reports.web.dto.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WidgetService {

    private WidgetRepository widgetRepository;

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
}
