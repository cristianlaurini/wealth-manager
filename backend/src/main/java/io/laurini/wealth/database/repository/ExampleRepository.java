package io.laurini.wealth.database.repository;

import io.laurini.wealth.database.model.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExampleRepository extends CrudRepository<Example, UUID> {

    @Override
    List<Example> findAll();

}
