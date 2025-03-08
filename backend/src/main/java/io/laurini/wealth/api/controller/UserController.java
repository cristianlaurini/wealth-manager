package io.laurini.wealth.api.controller;

import io.laurini.wealth.api.dto.UserDTO;
import io.laurini.wealth.operation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("register")
    @Operation(summary = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO input) {
        input.setPassword(encoder.encode(input.getPassword()));
        return ResponseEntity.ok(service.register(input));
    }

    @PostMapping("login")
    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    public ResponseEntity<String> login(@RequestBody UserDTO input) {
        return ResponseEntity.ok(service.authenticate(input));
    }

}
