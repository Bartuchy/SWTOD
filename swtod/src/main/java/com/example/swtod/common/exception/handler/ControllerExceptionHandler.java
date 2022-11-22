package com.example.swtod.common.exception.handler;

import com.example.swtod.common.exception.PasswordsNotEqualException;
import com.example.swtod.common.exception.UserNotFoundException;
import com.example.swtod.common.exception.UsernameTakenException;
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
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException exception) {

        ErrorResponse error = constructErrorResponse(exception, "User not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordsNotEqualException.class)
    public final ResponseEntity<ErrorResponse> handlePasswordsNotEqualException(
            PasswordsNotEqualException exception) {

        ErrorResponse error = constructErrorResponse(
                exception,
                "Given passwords are not equal");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameTakenException.class)
    public final ResponseEntity<ErrorResponse> handleUsernameTakenException(UsernameTakenException exception) {
        ErrorResponse error = constructErrorResponse(exception, "This email is already taken");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    private ErrorResponse constructErrorResponse(RuntimeException exception, String message) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        return new ErrorResponse(message, details);
    }

}
