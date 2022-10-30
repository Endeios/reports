package io.endeios.reports.web.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class Widget extends RepresentationModel<Widget> {
    public Widget(String origin, String name){
        this.origin = origin;
        this.name = name;
    }
    private String name;
    private String origin;
}
