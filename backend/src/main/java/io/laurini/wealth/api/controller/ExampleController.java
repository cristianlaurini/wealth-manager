package io.laurini.wealth.api.controller;

import io.laurini.wealth.api.dto.ExampleDTO;
import io.laurini.wealth.business.service.ExampleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class ExampleController {

    private final ExampleService service;

    public ExampleController(ExampleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ExampleDTO> sample() {
        return ResponseEntity.ok(this.service.example());
    }

}
