package io.laurini.wealth.api.controller;

import io.laurini.wealth.api.dto.ExampleDTO;
import io.laurini.wealth.operation.service.ExampleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
@SecurityRequirement(name = "bearerAuth")
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
