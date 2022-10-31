package io.endeios.reports.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class WidgetData {
    private String origin;
    private String name;
    private List<DataPoint> points;
}
