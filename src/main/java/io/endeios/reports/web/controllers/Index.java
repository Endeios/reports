package io.endeios.reports.web.controllers;

import io.endeios.reports.appLogic.WidgetService;
import io.endeios.reports.appLogic.exceptions.NoSuchOriginException;
import io.endeios.reports.appLogic.exceptions.NoSuchWidgetException;
import io.endeios.reports.web.dto.Widget;
import io.endeios.reports.web.dto.WidgetData;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/")
public class Index {

    private final WidgetService widgetService;

    public Index(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @GetMapping
    public CollectionModel<Widget> get(){
        List<Widget> widgets = widgetService.getAll();
        return CollectionModel.of(widgets,linkTo(Index.class).withSelfRel());
    }

    @RequestMapping(value = "/{origin}",method = RequestMethod.GET)
    public CollectionModel<Widget> getWidgetsOfAnOrigin(@PathVariable("origin") String origin){
            List<Widget> widgets = widgetService.getWidgetsOf(origin);
            return CollectionModel.of(widgets, linkTo(Index.class).withSelfRel());
    }

    @RequestMapping(value = "/{origin}/{name}", method = RequestMethod.GET)
    public EntityModel<WidgetData> getWidgetByOriginAnName(
            @PathVariable("origin") String origin,
            @PathVariable("name") String name){
        return EntityModel.of(widgetService.getWidgetData(origin, name));
    }

    @ExceptionHandler
    public ResponseEntity<NoSuchOriginException> handleException(NoSuchOriginException exception){
        return new ResponseEntity<>(exception, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<NoSuchWidgetException> handleException(NoSuchWidgetException exception){
        return new ResponseEntity<>(exception, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
