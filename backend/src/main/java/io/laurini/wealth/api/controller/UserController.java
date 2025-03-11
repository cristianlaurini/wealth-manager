package io.laurini.wealth.api.controller;

import io.laurini.wealth.api.dto.ExceptionDTO;
import io.laurini.wealth.api.dto.UserDTO;
import io.laurini.wealth.operation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "OK",
                content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ExceptionDTO.class)
                )
        )
})
public class UserController {

    private final UserService service;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("register")
    @Operation(summary = "Create a new User")
    @ApiResponse(
            responseCode = "409",
            description = "User can't be registered",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDTO.class)
            )
    )
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO input) {
        input.setPassword(encoder.encode(input.getPassword()));
        service.register(input);
        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    @Operation(summary = "Login")
    @ApiResponse(
            responseCode = "401",
            description = "Bad Credentials",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionDTO.class)
            )
    )
    public ResponseEntity<String> login(@RequestBody UserDTO input) {
        return ResponseEntity.ok(service.authenticate(input));
    }

}
