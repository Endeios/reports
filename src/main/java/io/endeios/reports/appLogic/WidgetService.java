package io.endeios.reports.appLogic;

import io.endeios.reports.appLogic.queries.WidgetRepository;
import io.endeios.reports.web.dto.Widget;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WidgetService {

    private WidgetRepository widgetRepository;

    public WidgetService(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }


    public List<Widget> getAll() {
        return new ArrayList<>(widgetRepository.getAll());
    }
}
