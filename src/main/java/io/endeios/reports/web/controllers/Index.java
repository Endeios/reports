package io.endeios.reports.web.controllers;

import io.endeios.reports.appLogic.WidgetService;
import io.endeios.reports.web.dto.Widget;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/")
public class Index {

    private WidgetService widgetService;

    public Index(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @GetMapping
    public CollectionModel<Widget> get(){
        List<Widget> widgets = widgetService.getAll();
        return CollectionModel.of(widgets,linkTo(Index.class).withSelfRel());
    }
}
