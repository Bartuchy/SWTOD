package com.example.swtod.configs.exception.handler;

import com.example.swtod.configs.exception.PasswordsNotEqualException;
import com.example.swtod.configs.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse error = constructErrorResponse(exception, "User not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordsNotEqualException.class)
    public final ResponseEntity<ErrorResponse> handlePasswordsNotEqualException(PasswordsNotEqualException exception) {
        ErrorResponse error = constructErrorResponse(exception, "Given passwords are not equal");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse constructErrorResponse(RuntimeException exception, String message) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        return new ErrorResponse(message, details);
    }

}