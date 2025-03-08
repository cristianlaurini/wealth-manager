package io.laurini.wealth.operation.service;

import io.laurini.wealth.api.dto.UserDTO;
import io.laurini.wealth.persistence.model.User;
import io.laurini.wealth.persistence.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JWTService jwt;
    private final UserRepository repository;
    private final AuthenticationManager authentication;

    public UserService(JWTService jwt, UserRepository repository, AuthenticationManager authentication) {
        this.jwt = jwt;
        this.repository = repository;
        this.authentication = authentication;
    }

    public UserDTO register(UserDTO input) {
        User entity = User.builder().username(input.getUsername()).password(input.getPassword()).build();
        repository.save(entity);
        return input;
    }

    public String authenticate(UserDTO input) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                input.getUsername(),
                input.getPassword()
        );
        authentication.authenticate(token);
        return jwt.generate(input.getUsername());
    }


}
