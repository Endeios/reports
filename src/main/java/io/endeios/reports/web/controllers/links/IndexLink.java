package io.endeios.reports.web.controllers.links;

import org.springframework.hateoas.LinkRelation;

public class IndexLink implements LinkRelation {

    @Override
    public String value() {
        return "index";
    }
}