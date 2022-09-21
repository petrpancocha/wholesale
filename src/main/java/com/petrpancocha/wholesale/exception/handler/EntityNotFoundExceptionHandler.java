package com.petrpancocha.wholesale.exception.handler;


import com.petrpancocha.wholesale.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponseDto handleException(EntityNotFoundException ex) {
        return new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage());
    }
}
