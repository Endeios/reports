package io.endeios.reports.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@AllArgsConstructor
public class WidgetData extends RepresentationModel<WidgetData> {

    private String origin;
    private String name;
    private List<DataPoint> points;
}
