package io.endeios.reports.web.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class Widget extends RepresentationModel<Widget> {
    public Widget(String name){
        this.name = name;
    }
    private String name;
}
