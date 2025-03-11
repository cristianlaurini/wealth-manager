package io.laurini.wealth.persistence.repository;

import io.laurini.wealth.persistence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>  {

    User findByUsername(String username);
    Boolean existsByUsername(String username);

}
