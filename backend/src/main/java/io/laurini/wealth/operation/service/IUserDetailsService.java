package io.laurini.wealth.operation.service;

import io.laurini.wealth.operation.pojo.UserPrincipal;
import io.laurini.wealth.persistence.model.User;
import io.laurini.wealth.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public IUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (Objects.isNull(user)) throw new UsernameNotFoundException("user not found");
        return new UserPrincipal(user);
    }

}
