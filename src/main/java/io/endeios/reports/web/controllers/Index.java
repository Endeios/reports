package io.endeios.reports.web.controllers;

import io.endeios.reports.web.dto.Widget;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/")
public class Index {

    @GetMapping
    public CollectionModel<Widget> get(){
        ArrayList<Widget> widgets = new ArrayList<Widget>();

        widgets.add(new Widget("origin", "test"));
        return CollectionModel.of(widgets,linkTo(Index.class).withSelfRel());
    }
}
