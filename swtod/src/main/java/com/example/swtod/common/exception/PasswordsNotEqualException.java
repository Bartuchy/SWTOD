package com.example.swtod.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordsNotEqualException extends RuntimeException {
    public PasswordsNotEqualException(String message) {
        super(message);
        log.info(message);
    }
}
