package io.laurini.wealth.util.handler;

import io.laurini.wealth.api.dto.ExceptionDTO;
import io.laurini.wealth.util.exception.UserAlreadyRegisteredException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler implements ApplicationEventPublisherAware {

    protected ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(@NotNull ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionDTO> handle(UserAlreadyRegisteredException e) {
        ExceptionDTO output = ExceptionDTO
                .builder()
                .code(409)
                .description("User Already Registered")
                .exception(e.getClass().getName())
                .build();
        return ResponseEntity
                .status(409)
                .body(output);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDTO> handle(BadCredentialsException e) {
        ExceptionDTO output = ExceptionDTO
                .builder()
                .code(401)
                .description("Bad Credentials")
                .exception(e.getClass().getName())
                .build();
        return ResponseEntity
                .status(401)
                .body(output);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handle(Exception e) {
        ExceptionDTO output = ExceptionDTO
                .builder()
                .code(500)
                .description(StringUtils.join("There was an error: ", e.getLocalizedMessage()))
                .exception(e.getClass().getName())
                .build();
        return ResponseEntity
                .internalServerError()
                .body(output);
    }

}
