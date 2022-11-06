package io.endeios.reports.web.controllers;

import io.endeios.reports.appLogic.WidgetService;
import io.endeios.reports.appLogic.exceptions.NoSuchOriginException;
import io.endeios.reports.appLogic.exceptions.NoSuchWidgetException;
import io.endeios.reports.web.controllers.links.IndexLink;
import io.endeios.reports.web.dto.Widget;
import io.endeios.reports.web.dto.WidgetData;
import io.endeios.reports.web.dto.errors.Error;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
public class Index {

    private final WidgetService widgetService;

    public Index(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @GetMapping
    public CollectionModel<Widget> get() {
        List<Widget> widgets = widgetService.getAll();
        addSelfLinkToWidgets(widgets);
        return CollectionModel.of(widgets, linkTo(Index.class).withSelfRel());
    }

    private static void addSelfLinkToWidgets(List<Widget> widgets) {
        for (Widget widget :
                widgets) {
            widget.add(linkTo(methodOn(Index.class).getWidgetByOriginAnName(widget.getOrigin(), widget.getName())).withSelfRel());
        }
    }

    @RequestMapping(value = "/{origin}", method = RequestMethod.GET)
    public CollectionModel<Widget> getWidgetsOfAnOrigin(@PathVariable("origin") String origin) {
        List<Widget> widgets = widgetService.getWidgetsOf(origin);
        addSelfLinkToWidgets(widgets);
        return CollectionModel.of(widgets, linkTo(Index.class).withSelfRel());
    }

    @RequestMapping(value = "/{origin}/{name}", method = RequestMethod.GET)
    public EntityModel<WidgetData> getWidgetByOriginAnName(
            @PathVariable("origin") String origin,
            @PathVariable("name") String name) {
        EntityModel<WidgetData> widget = EntityModel.of(widgetService.getWidgetData(origin, name));
        widget.add(linkTo(methodOn(Index.class).getWidgetByOriginAnName(origin, name)).withSelfRel());
        widget.add(linkTo(methodOn(Index.class).get()).withRel(new IndexLink()));
        return widget;
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(NoSuchOriginException exception) {
        return getErrorResponseEntity(exception);
    }

    private static ResponseEntity<Error> getErrorResponseEntity(RuntimeException exception) {
        Error error = new Error(exception);
        Link indexLink = linkTo(methodOn(Index.class).get()).withRel(new IndexLink());
        error.add(indexLink);
        return new ResponseEntity<>(error, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(NoSuchWidgetException exception) {
        return getErrorResponseEntity(exception);
    }
}
