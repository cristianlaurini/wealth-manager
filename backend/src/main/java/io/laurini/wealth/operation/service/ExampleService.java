package io.laurini.wealth.operation.service;

import io.laurini.wealth.api.dto.ExampleDTO;
import io.laurini.wealth.persistence.model.Example;
import io.laurini.wealth.persistence.repository.ExampleRepository;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private final ExampleRepository repository;

    public ExampleService(ExampleRepository repository) {
        this.repository = repository;
    }

    public ExampleDTO example() {
        Example entity = repository.findAll().get(0);
        return ExampleDTO.builder().output(entity.getExample()).build();
    }

}
