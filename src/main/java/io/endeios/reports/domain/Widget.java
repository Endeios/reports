package io.endeios.reports.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@EqualsAndHashCode
@ToString
public class Widget {
    public Widget(String origin, String name){
        this.origin = origin;
        this.name = name;
    }
    private String name;
    private String origin;
}
