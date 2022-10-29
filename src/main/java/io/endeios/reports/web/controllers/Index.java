package io.endeios.reports.web.controllers;

import io.endeios.reports.web.dto.Widget;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Index {

    @GetMapping
    public Widget get(){
        return new Widget("test");
    }
}
